package io.github.milkdrinkers.versionwatch.platform.polymart;

import io.github.milkdrinkers.versionwatch.platform.Config;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

public class ConfigPolymart extends Config implements VersionWatchConfig {
    private final String resourceId;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    ConfigPolymart(@NotNull String userAgent, @NotNull String resourceId, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent);
        this.resourceId = resourceId;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getLatestReleaseLink() {
        return latestReleaseLink;
    }

    public String getLatestReleaseAPI() {
        return latestReleaseAPI;
    }
}
