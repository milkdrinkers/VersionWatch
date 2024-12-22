package io.github.milkdrinkers.versionwatch.platform.github;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigGithubBuilder extends ConfigBuilder<ConfigGithubBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://github.com/%s/%s/releases/latest";
    private final static String LATEST_RELEASE_API = "https://api.github.com/repos/%s/%s/releases/latest";

    private @Nullable String githubOwner;
    private @Nullable String githubRepo;

    public @NotNull ConfigGithubBuilder withOwner(@NotNull String githubUser) {
        this.githubOwner = githubUser;
        return this;
    }

    public @NotNull ConfigGithubBuilder withRepo(@NotNull String githubRepo) {
        this.githubRepo = githubRepo;
        return this;
    }

    public ConfigGithub build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (githubOwner == null)
            throw new ConfigException("Missing required field github owner in version watch config!");

        if (githubRepo == null)
            throw new ConfigException("Missing required field github repository in version watch config!");

        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, githubOwner, githubRepo);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, githubOwner, githubRepo);

        return new ConfigGithub(super.getUserAgent(), githubOwner, githubRepo, latestReleaseLink, latestReleaseAPI);
    }
}