package io.github.milkdrinkers.versionwatch.platform.spigot;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ConfigSpigot extends PlatformConfig implements VersionWatchConfig {
    private final @NotNull String projectSlug;

    ConfigSpigot(@NotNull String userAgent, @NotNull String projectSlug, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent, latestReleaseLink, latestReleaseAPI);
        this.projectSlug = projectSlug;
    }

    public String getProjectSlug() {
        return projectSlug;
    }
}
