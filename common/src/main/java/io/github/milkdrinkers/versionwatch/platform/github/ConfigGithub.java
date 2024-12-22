package io.github.milkdrinkers.versionwatch.platform.github;

import io.github.milkdrinkers.versionwatch.platform.Config;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

public class ConfigGithub extends Config implements VersionWatchConfig {
    private final String githubOwner;
    private final String githubRepo;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    ConfigGithub(@NotNull String userAgent, @NotNull String githubOwner, @NotNull String githubRepo, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent);
        this.githubOwner = githubOwner;
        this.githubRepo = githubRepo;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getGithubOwner() {
        return githubOwner;
    }

    public String getGithubRepo() {
        return githubRepo;
    }

    public String getLatestReleaseLink() {
        return latestReleaseLink;
    }

    public String getLatestReleaseAPI() {
        return latestReleaseAPI;
    }
}
