package io.github.milkdrinkers.versionwatch.platform.hangar;

import io.github.milkdrinkers.versionwatch.platform.PlatformConfig;
import io.github.milkdrinkers.versionwatch.platform.VersionWatchConfig;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ConfigHangar extends PlatformConfig implements VersionWatchConfig {
    private final @NotNull String githubUser;
    private final @NotNull String githubRepo;

    ConfigHangar(@NotNull String userAgent, @NotNull String githubUser, @NotNull String githubRepo, @NotNull String latestReleaseLink, @NotNull String latestReleaseAPI) {
        super(userAgent, latestReleaseLink, latestReleaseAPI);
        this.githubUser = githubUser;
        this.githubRepo = githubRepo;
    }

    public String getGithubUser() {
        return githubUser;
    }

    public String getGithubRepo() {
        return githubRepo;
    }
}
