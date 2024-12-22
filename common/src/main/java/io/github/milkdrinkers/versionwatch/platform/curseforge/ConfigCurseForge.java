package io.github.milkdrinkers.versionwatch.platform.curseforge;

import io.github.milkdrinkers.versionwatch.platform.Config;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

public class ConfigCurseForge extends Config implements VersionWatchConfig {
    private final String apiKey;
    private final String projectId;
    private final String projectSlug;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    ConfigCurseForge(@NotNull String userAgent, @NotNull String apiKey, @NotNull String projectId, @NotNull String projectSlug, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent);
        this.apiKey = apiKey;
        this.projectId = projectId;
        this.projectSlug = projectSlug;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getProjectId() {
        return projectId;
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
