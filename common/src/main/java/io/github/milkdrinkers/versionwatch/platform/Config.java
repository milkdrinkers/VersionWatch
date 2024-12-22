package io.github.milkdrinkers.versionwatch.platform;

public abstract class Config {
    private final String userAgent;

    protected Config(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
