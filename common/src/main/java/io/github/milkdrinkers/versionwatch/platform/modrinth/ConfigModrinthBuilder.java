package io.github.milkdrinkers.versionwatch.platform.modrinth;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigModrinthBuilder extends PlatformConfigBuilder<ConfigModrinthBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://modrinth.com/project/%s/version/latest";
    private final static String LATEST_RELEASE_API = "https://api.modrinth.com/v2/project/%s/version";

    private @Nullable String project; // Can be the project id or slug

    public @NotNull ConfigModrinthBuilder withProjectSlug(@Nullable String projectSlug) {
        if (projectSlug != null)
            this.project = projectSlug;
        return this;
    }

    public @NotNull ConfigModrinthBuilder withProjectId(@Nullable String projectId) {
        if (projectId != null)
            this.project = projectId;
        return this;
    }

    public @NotNull ConfigModrinth build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (project == null)
            throw new ConfigException("Missing required field project slug/project id in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, project);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, project);

        return new ConfigModrinth(super.getUserAgent(), project, latestReleaseLink, latestReleaseAPI);
    }
}