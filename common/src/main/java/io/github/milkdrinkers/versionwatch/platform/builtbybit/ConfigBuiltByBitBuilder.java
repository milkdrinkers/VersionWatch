package io.github.milkdrinkers.versionwatch.platform.builtbybit;

import io.github.milkdrinkers.versionwatch.platform.ConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigBuiltByBitBuilder extends ConfigBuilder<ConfigBuiltByBitBuilder> {
    private final static String LATEST_RELEASE_LINK = "https://builtbybit.com/resources/%s/updates";
    private final static String LATEST_RELEASE_API = "https://api.builtbybit.com/v1/resources/%s/versions/latest";

    private @Nullable String token;
    private @Nullable TokenType tokenType;
    private @Nullable String resourceId;

    public @NotNull ConfigBuiltByBitBuilder withToken(@NotNull String token) {
        this.token = token;
        return this;
    }

    public @NotNull ConfigBuiltByBitBuilder withTokenType(@NotNull TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public @NotNull ConfigBuiltByBitBuilder withResource(@NotNull String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ConfigBuiltByBit build() throws ConfigException {
        if (super.getUserAgent() == null)
            throw new ConfigException("Missing required field user agent in version watch config!");

        if (token == null)
            throw new ConfigException("Missing required field token in version watch config!");

        if (tokenType == null)
            throw new ConfigException("Missing required field token type in version watch config!");

        if (resourceId == null)
            throw new ConfigException("Missing required field resource id in version watch config!");

        final String resultingToken = String.format("%s %s", tokenType.getTokenTypeName(), token);
        final String latestReleaseLink = String.format(LATEST_RELEASE_LINK, resourceId);
        final String latestReleaseAPI = String.format(LATEST_RELEASE_API, resourceId);

        return new ConfigBuiltByBit(super.getUserAgent(), resultingToken, resourceId, latestReleaseLink, latestReleaseAPI);
    }
}