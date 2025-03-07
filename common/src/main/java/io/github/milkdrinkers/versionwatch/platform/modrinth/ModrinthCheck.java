package io.github.milkdrinkers.versionwatch.platform.modrinth;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.platform.PlatformImplementation;
import io.github.milkdrinkers.versionwatch.platform.exception.BadResponseException;
import io.github.milkdrinkers.versionwatch.platform.exception.BadStatusCodeException;
import io.github.milkdrinkers.versionwatch.platform.exception.VersionWatchException;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class ModrinthCheck implements PlatformImplementation {
    private final ConfigModrinth config;

    public ModrinthCheck(final ConfigModrinth config) {
        this.config = config;
    }

    @Override
    public Version fetchLatestVersion() throws VersionWatchException {
        final HttpClient httpClient = HttpClient.newHttpClient();
        final HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(config.getLatestReleaseAPI()))
            .timeout(Duration.ofSeconds(10))
            .header("Accept", "application/json")
            .header("User-Agent", config.getUserAgent())
            .build();

        try {
            final HttpResponse<InputStream> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofInputStream());

            if (resp.statusCode() != 200)
                throw new BadStatusCodeException("Failed to fetch version info: " + resp.statusCode());

            return parseResponse(resp.body());
        } catch (IOException e) {
            throw new BadResponseException("IOException during update check: ", e);
        } catch (InterruptedException e) {
            throw new BadResponseException("InterruptedException during update check: ", e);
        } catch (SecurityException e) {
            throw new BadResponseException("SecurityException during update check: ", e);
        }
    }

    @Override
    public CompletableFuture<Version> fetchLatestVersionAsync() throws VersionWatchException {
        return CompletableFuture.supplyAsync(this::fetchLatestVersion);
    }

    @Override
    public @Nullable Version parseResponse(final InputStream inputStream) throws BadResponseException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            final JsonArray versions = JsonParser.parseReader(reader).getAsJsonArray();

            if (versions.isEmpty())
                throw new BadResponseException("Versions array is empty!");

            // First version in the array is the latest
            final JsonObject latestVersion = versions.get(0).getAsJsonObject();

            final String version = latestVersion.getAsJsonPrimitive("version_number").getAsString().toUpperCase();

            return Version.of(version);
        } catch (Exception e) {
            throw new BadResponseException("Failed to parse version JSON response.", e);
        }
    }
}
