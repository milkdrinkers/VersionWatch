package io.github.milkdrinkers.versionwatch.platform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ConfigBuilder<T extends ConfigBuilder<T>> {
    private @Nullable String userAgent;

    @SuppressWarnings("unchecked")
    public @NotNull T withUserAgent(@NotNull String userAgent) {
        this.userAgent = userAgent;
        return (T) this;
    }

    protected @Nullable String getUserAgent() {
        return this.userAgent;
    }
}
