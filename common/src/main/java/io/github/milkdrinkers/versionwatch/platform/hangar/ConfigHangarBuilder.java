package io.github.milkdrinkers.versionwatch.platform.hangar;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigHangarBuilder extends ConfigBuilder<ConfigHangarBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://hangar.papermc.io/%s/%s/versions";
    private final static String LATEST_RELEASE_API = "https://hangar.papermc.io/api/v1/projects/%s/latestrelease";

    private @Nullable String projectOwner;
    private @Nullable String projectSlug;

    public @NotNull ConfigHangarBuilder withOwner(@NotNull String projectOwner) {
        this.projectOwner = projectOwner;
        return this;
    }

    public @NotNull ConfigHangarBuilder withProject(@NotNull String projectSlug) {
        this.projectSlug = projectSlug;
        return this;
    }

    public ConfigHangar build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (projectOwner == null)
            throw new ConfigException("Missing required field github user in version watch config!");

        if (projectSlug == null)
            throw new ConfigException("Missing required field github repository in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, projectOwner, projectSlug);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, projectSlug);

        return new ConfigHangar(super.getUserAgent(), projectOwner, projectSlug, latestReleaseLink, latestReleaseAPI);
    }
}