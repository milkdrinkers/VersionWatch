package io.github.milkdrinkers.versionwatch.platform.builtbybit;

import io.github.milkdrinkers.versionwatch.platform.Config;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

public class ConfigBuiltByBit extends Config implements VersionWatchConfig {
    private final String token;
    private final String resourceId;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    ConfigBuiltByBit(@NotNull String userAgent, @NotNull String token, @NotNull String resourceId, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent);
        this.token = token;
        this.resourceId = resourceId;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getToken() {
        return token;
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
