package io.github.milkdrinkers.versionwatch.platform.polymart;

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

public class PolymartCheck implements PlatformImplementation {
    private final ConfigPolymart config;

    public PolymartCheck(final ConfigPolymart config) {
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
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", config.getUserAgent());

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
            final JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

            final JsonObject resourceObject = json.getAsJsonObject("resource");
            if (resourceObject == null)
                throw new BadResponseException("Resource JSON object was null or empty!");

            final JsonObject updatesObject = resourceObject.getAsJsonObject("updates");
            if (updatesObject == null || updatesObject.isEmpty())
                throw new BadResponseException("Updates JSON object was null or empty!");

            final JsonObject latestObject = updatesObject.getAsJsonObject("latest");
            if (latestObject == null || updatesObject.isEmpty())
                throw new BadResponseException("Latest JSON object was null or empty!");

            final String version = latestObject.getAsJsonPrimitive("version").getAsString().toUpperCase();

            return Version.of(version);
        } catch (Exception e) {
            throw new BadResponseException("Failed to parse version JSON response.", e);
        }
    }
}
