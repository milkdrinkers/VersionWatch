package io.github.milkdrinkers.versionwatch.platform.polymart;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ConfigPolymart extends PlatformConfig implements VersionWatchConfig {
    private final @NotNull String resourceId;

    ConfigPolymart(@NotNull String userAgent, @NotNull String resourceId, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent, latestReleaseLink, latestReleaseAPI);
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }
}
