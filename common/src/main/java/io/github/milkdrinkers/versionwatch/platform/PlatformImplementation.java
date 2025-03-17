package io.github.milkdrinkers.versionwatch.platform;

import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.Platform;
import io.github.milkdrinkers.versionwatch.platform.exception.BadResponseException;
import io.github.milkdrinkers.versionwatch.platform.exception.VersionWatchException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

/**
 * A platform implementation can fetch the latest version from a platform.
 */
@SuppressWarnings("unused")
public interface PlatformImplementation {
    /**
     * Get the config used by this platform implementation.
     *
     * @return config
     */
    @NotNull PlatformConfig getConfig();

    /**
     * Get the platform used by this platform implementation.
     *
     * @return platform
     */
    @NotNull Platform getPlatform();

    /**
     * Fetch the latest version from the platform.
     *
     * @return version
     * @throws VersionWatchException when failing to fetch
     * @see Version
     */
    @Nullable Version fetchLatestVersion() throws VersionWatchException;

    /**
     * Fetch the latest version from the platform.
     *
     * @return version
     * @throws VersionWatchException when failing to fetch
     * @see Version
     */
    @NotNull CompletableFuture<@Nullable Version> fetchLatestVersionAsync() throws VersionWatchException;

    /**
     * Parses the http response and json into a {@link Version}.
     *
     * @param inputStream http response stream
     * @return version
     * @throws BadResponseException on failure to read response
     */
    @Nullable Version parseResponse(final InputStream inputStream) throws BadResponseException;
}
