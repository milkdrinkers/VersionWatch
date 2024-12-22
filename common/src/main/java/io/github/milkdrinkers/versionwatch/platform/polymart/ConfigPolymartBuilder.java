package io.github.milkdrinkers.versionwatch.platform.polymart;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigPolymartBuilder extends ConfigBuilder<ConfigPolymartBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://polymart.org/resource/%s/updates";
    private final static String LATEST_RELEASE_API = "https://api.polymart.org/v1/getResourceInfo/?resource_id=%s";

    private @Nullable String resourceId;

    public @NotNull ConfigPolymartBuilder withResource(@NotNull String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ConfigPolymart build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (resourceId == null)
            throw new ConfigException("Missing required field resource id in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, resourceId);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, resourceId);

        return new ConfigPolymart(super.getUserAgent(), resourceId, latestReleaseLink, latestReleaseAPI);
    }
}