package io.github.milkdrinkers.versionwatch.platform.hangar;

import io.github.milkdrinkers.versionwatch.platform.Config;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

public class ConfigHangar extends Config implements VersionWatchConfig {
    private final String githubUser;
    private final String githubRepo;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    ConfigHangar(@NotNull String userAgent, @NotNull String githubUser, @NotNull String githubRepo, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent);
        this.githubUser = githubUser;
        this.githubRepo = githubRepo;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getGithubUser() {
        return githubUser;
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
