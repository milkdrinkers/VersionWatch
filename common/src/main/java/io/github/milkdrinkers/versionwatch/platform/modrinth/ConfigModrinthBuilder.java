package io.github.milkdrinkers.versionwatch.platform.modrinth;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigModrinthBuilder extends ConfigBuilder {
    private final static String LATEST_RELEASE_LINK = "https://modrinth.com/project/%s/version/latest";
    private final static String LATEST_RELEASE_API = "https://api.modrinth.com/v2/project/%s/version";

    private @Nullable String projectSlug;

    public @NotNull ConfigModrinthBuilder withProject(@NotNull String projectSlug) {
        this.projectSlug = projectSlug;
        return this;
    }

    public ConfigModrinth build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (projectSlug == null)
            throw new ConfigException("Missing required field project slug in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, projectSlug);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, projectSlug);

        return new ConfigModrinth(super.getUserAgent(), projectSlug, latestReleaseLink, latestReleaseAPI);
    }
}