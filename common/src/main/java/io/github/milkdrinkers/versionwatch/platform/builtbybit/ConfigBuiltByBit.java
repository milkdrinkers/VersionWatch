package io.github.milkdrinkers.versionwatch.platform.builtbybit;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ConfigBuiltByBit extends PlatformConfig implements VersionWatchConfig {
    private final @NotNull String token;
    private final @NotNull String resourceId;

    ConfigBuiltByBit(@NotNull String userAgent, @NotNull String token, @NotNull String resourceId, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent, latestReleaseLink, latestReleaseAPI);
        this.token = token;
        this.resourceId = resourceId;
    }

    public String getToken() {
        return token;
    }

    public String getResourceId() {
        return resourceId;
    }
}
