package io.github.milkdrinkers.versionwatch.platform.curseforge;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class CurseForgeCheck implements PlatformImplementation {
    private final ConfigCurseForge config;

    public CurseForgeCheck(final ConfigCurseForge config) {
        this.config = config;
    }

    @Override
    public Version fetchLatestVersion() throws VersionWatchException {
        try {
            final URL url = new URL(config.getLatestReleaseAPI());
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("x-api-key", config.getApiKey());
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", config.getUserAgent());
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();

            if (responseCode != 200)
                throw new BadStatusCodeException("Failed to fetch version info: " + responseCode);

            return parseResponse(connection.getInputStream());
        } catch (IOException e) {
            throw new BadResponseException("IOException during update check: ", e);
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
            final JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            final JsonArray files = jsonObject.getAsJsonArray("data");

            if (files.isEmpty())
                throw new BadResponseException("Versions array is empty!");

            // First version in the array is the latest
            final JsonObject latestVersion = files.get(0).getAsJsonObject();

            final String version = latestVersion.getAsJsonPrimitive("displayName").getAsString().toUpperCase();

            return Version.of(version);
        } catch (Exception e) {
            throw new BadResponseException("Failed to parse version JSON response.", e);
        }
    }
}
