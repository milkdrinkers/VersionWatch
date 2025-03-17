package io.github.milkdrinkers.versionwatch.platform.spigot;

import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.Platform;
import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.PlatformImplementation;
import io.github.milkdrinkers.versionwatch.platform.exception.BadResponseException;
import io.github.milkdrinkers.versionwatch.platform.exception.BadStatusCodeException;
import io.github.milkdrinkers.versionwatch.platform.exception.VersionWatchException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

public class SpigotCheck implements PlatformImplementation {
    private final ConfigSpigot config;

    public SpigotCheck(final ConfigSpigot config) {
        this.config = config;
    }

    @Override
    public @NotNull PlatformConfig getConfig() {
        return config;
    }

    @Override
    public @NotNull Platform getPlatform() {
        return Platform.Spigot;
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

            final JSONObject versionData = new JSONObject(content);

            final String version = versionData.getString("name").toUpperCase();

            return Version.of(version);
        } catch (Exception e) {
            throw new BadResponseException("Failed to parse version JSON response.", e);
        }
    }
}
