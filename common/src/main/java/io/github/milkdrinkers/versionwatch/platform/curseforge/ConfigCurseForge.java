package io.github.milkdrinkers.versionwatch.platform.curseforge;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ConfigCurseForge extends PlatformConfig implements VersionWatchConfig {
    private final @NotNull String apiKey;
    private final @NotNull String projectId;
    private final @NotNull String projectSlug;

    ConfigCurseForge(@NotNull String userAgent, @NotNull String apiKey, @NotNull String projectId, @NotNull String projectSlug, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent, latestReleaseLink, latestReleaseAPI);
        this.apiKey = apiKey;
        this.projectId = projectId;
        this.projectSlug = projectSlug;
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
}
