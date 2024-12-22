package io.github.milkdrinkers.versionwatch.platform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ConfigBuilder {
    private @Nullable String userAgent;

    public @NotNull ConfigBuilder withUserAgent(@NotNull String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    protected @Nullable String getUserAgent() {
        return this.userAgent;
    }
}
