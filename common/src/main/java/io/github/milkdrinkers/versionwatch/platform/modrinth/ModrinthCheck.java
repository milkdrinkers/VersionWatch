package io.github.milkdrinkers.versionwatch.platform.modrinth;

import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.Platform;
import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.PlatformImplementation;
import io.github.milkdrinkers.versionwatch.platform.exception.BadResponseException;
import io.github.milkdrinkers.versionwatch.platform.exception.BadStatusCodeException;
import io.github.milkdrinkers.versionwatch.platform.exception.VersionWatchException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModrinthCheck implements PlatformImplementation {
    private final ConfigModrinth config;

    public ModrinthCheck(final ConfigModrinth config) {
        this.config = config;
    }

    @Override
    public @NotNull PlatformConfig getConfig() {
        return config;
    }

    @Override
    public @NotNull Platform getPlatform() {
        return Platform.Modrinth;
    }

    @Override
    public @Nullable Version fetchLatestVersion() throws VersionWatchException {
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
    public @NotNull CompletableFuture<@Nullable Version> fetchLatestVersionAsync() throws VersionWatchException {
        return CompletableFuture
            .supplyAsync(this::fetchLatestVersion)
            .exceptionally(throwable -> null);
    }

    @Override
    public @Nullable Version parseResponse(final @NotNull InputStream inputStream) throws BadResponseException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            final String content = reader.lines().collect(Collectors.joining());

            final JSONArray releases = new JSONArray(content);

            if (releases.isEmpty())
                throw new BadResponseException("Releases array is empty!");

            // Find first release in array
            for (int i = 0; i < releases.length(); i++) {
                final JSONObject release = releases.getJSONObject(i);

                // Ignore non-releases
                if (!release.getString("version_type").equals("release"))
                    continue;

                final String version = release.getString("version_number").toUpperCase();

                return Version.of(version);
            }

            return null;
        } catch (Exception e) {
            throw new BadResponseException("Failed to parse version JSON response.", e);
        }
    }
}
