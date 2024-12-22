package io.github.milkdrinkers.versionwatch.platform.curseforge;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigCurseForgeBuilder extends ConfigBuilder<ConfigCurseForgeBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://www.curseforge.com/minecraft/mc-mods/%s/files";
    private final static String LATEST_RELEASE_API = "https://api.curseforge.com/v1/mods/%s/files";

    private @Nullable String apiKey;
    private @Nullable String projectId;
    private @Nullable String projectSlug;

    public @NotNull ConfigCurseForgeBuilder withApiKey(@NotNull String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public @NotNull ConfigCurseForgeBuilder withProject(@NotNull String projectId) {
        this.projectId = projectId;
        return this;
    }

    public @NotNull ConfigCurseForgeBuilder withSlug(@NotNull String projectSlug) {
        this.projectSlug = projectSlug;
        return this;
    }

    public ConfigCurseForge build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (apiKey == null)
            throw new ConfigException("Missing required field api key in version watch config!");

        if (projectId == null)
            throw new ConfigException("Missing required field project id in version watch config!");

        if (projectSlug == null)
            throw new ConfigException("Missing required field project slug in version watch config!");


        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, projectSlug);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, projectId);

        return new ConfigCurseForge(super.getUserAgent(), apiKey, projectId, projectSlug, latestReleaseLink, latestReleaseAPI);
    }
}