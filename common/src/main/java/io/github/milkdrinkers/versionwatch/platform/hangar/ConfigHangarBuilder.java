package io.github.milkdrinkers.versionwatch.platform.hangar;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigHangarBuilder extends PlatformConfigBuilder<ConfigHangarBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://hangar.papermc.io/%s/%s/versions";
    private final static String LATEST_RELEASE_API = "https://hangar.papermc.io/api/v1/projects/%s/latestrelease";

    private @Nullable String projectOwner;
    private @Nullable String project; // Can be the project id or slug

    public @NotNull ConfigHangarBuilder withOwner(@Nullable String projectOwner) {
        this.projectOwner = projectOwner;
        return this;
    }

    public @NotNull ConfigHangarBuilder withProjectSlug(@Nullable String projectSlug) {
        if (projectSlug != null)
            this.project = projectSlug;
        return this;
    }

    public @NotNull ConfigHangarBuilder withProjectId(@Nullable String projectId) {
        if (projectId != null)
            this.project = projectId;
        return this;
    }

    public @NotNull ConfigHangar build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (projectOwner == null)
            throw new ConfigException("Missing required field owner in version watch config!");

        if (project == null)
            throw new ConfigException("Missing required field projectId/projectSlug in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, projectOwner, project);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, project);

        return new ConfigHangar(super.getUserAgent(), projectOwner, project, latestReleaseLink, latestReleaseAPI);
    }
}