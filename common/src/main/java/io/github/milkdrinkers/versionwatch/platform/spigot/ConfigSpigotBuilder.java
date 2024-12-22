package io.github.milkdrinkers.versionwatch.platform.spigot;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigSpigotBuilder extends ConfigBuilder<ConfigSpigotBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://www.spigotmc.org/resources/%s/updates";
    private final static String LATEST_RELEASE_API = "https://api.spiget.org/v2/resources/%s/versions/latest";

    private @Nullable String resourceId;

    public @NotNull ConfigSpigotBuilder withResource(@NotNull String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ConfigSpigot build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (resourceId == null)
            throw new ConfigException("Missing required field resource id in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, resourceId);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, resourceId);

        return new ConfigSpigot(super.getUserAgent(), resourceId, latestReleaseLink, latestReleaseAPI);
    }
}