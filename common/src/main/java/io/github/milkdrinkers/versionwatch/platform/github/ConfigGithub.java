package io.github.milkdrinkers.versionwatch.platform.github;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ConfigGithub extends PlatformConfig implements VersionWatchConfig {
    private final @NotNull String githubOwner;
    private final @NotNull String githubRepo;

    ConfigGithub(@NotNull String userAgent, @NotNull String githubOwner, @NotNull String githubRepo, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent, latestReleaseLink, latestReleaseAPI);
        this.githubOwner = githubOwner;
        this.githubRepo = githubRepo;
    }

    public String getGithubOwner() {
        return githubOwner;
    }

    public String getGithubRepo() {
        return githubRepo;
    }

}
