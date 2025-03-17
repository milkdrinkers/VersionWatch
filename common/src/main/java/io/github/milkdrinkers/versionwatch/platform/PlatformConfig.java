package io.github.milkdrinkers.versionwatch.platform;

public abstract class PlatformConfig {
    private final String userAgent;
    private final String latestReleaseLink;
    private final String latestReleaseAPI;

    protected PlatformConfig(String userAgent, String latestReleaseLink, String latestReleaseAPI) {
        this.userAgent = userAgent;
        this.latestReleaseLink = latestReleaseLink;
        this.latestReleaseAPI = latestReleaseAPI;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getLatestReleaseLink() {
        return latestReleaseLink;
    }

    public String getLatestReleaseAPI() {
        return latestReleaseAPI;
    }
}
