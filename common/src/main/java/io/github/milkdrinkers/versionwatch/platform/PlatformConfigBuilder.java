package io.github.milkdrinkers.versionwatch.platform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PlatformConfigBuilder<T extends PlatformConfigBuilder<T>> {
    private @Nullable String userAgent = "VersionWatcher";

    @SuppressWarnings("unchecked")
    public @NotNull T withUserAgent(@NotNull String userAgent) {
        this.userAgent = userAgent;
        return (T) this;
    }

    protected @Nullable String getUserAgent() {
        return this.userAgent;
    }
}
