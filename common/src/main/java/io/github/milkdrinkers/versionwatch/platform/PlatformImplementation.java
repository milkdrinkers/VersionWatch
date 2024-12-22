package io.github.milkdrinkers.versionwatch.platform;

import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.platform.exception.BadResponseException;
import io.github.milkdrinkers.versionwatch.platform.exception.VersionWatchException;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public interface PlatformImplementation {
    Version fetchLatestVersion() throws VersionWatchException;
    CompletableFuture<Version> fetchLatestVersionAsync() throws VersionWatchException;
    @Nullable Version parseResponse(final InputStream inputStream) throws BadResponseException;
}
