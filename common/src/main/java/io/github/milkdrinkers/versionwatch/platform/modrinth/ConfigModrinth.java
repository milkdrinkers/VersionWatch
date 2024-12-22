package io.github.milkdrinkers.versionwatch.platform.modrinth;

import io.github.milkdrinkers.versionwatch.platform.Config;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

public class ConfigModrinth extends Config implements VersionWatchConfig {
    private final String projectSlug;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    ConfigModrinth(@NotNull String userAgent, @NotNull String projectSlug, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent);
        this.projectSlug = projectSlug;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getProjectSlug() {
        return projectSlug;
    }

    public String getLatestReleaseLink() {
        return latestReleaseLink;
    }

    public String getLatestReleaseAPI() {
        return latestReleaseAPI;
    }
}
