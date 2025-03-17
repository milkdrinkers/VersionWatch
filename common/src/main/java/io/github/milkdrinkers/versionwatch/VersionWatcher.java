package io.github.milkdrinkers.versionwatch;

import io.github.milkdrinkers.javasemver.Version;
import io.github.milkdrinkers.versionwatch.platform.PlatformConfigBuilder;
import io.github.milkdrinkers.versionwatch.platform.PlatformImplementation;
import io.github.milkdrinkers.versionwatch.platform.builtbybit.BuiltByBitCheck;
import io.github.milkdrinkers.versionwatch.platform.builtbybit.ConfigBuiltByBit;
import io.github.milkdrinkers.versionwatch.platform.builtbybit.ConfigBuiltByBitBuilder;
import io.github.milkdrinkers.versionwatch.platform.builtbybit.TokenType;
import io.github.milkdrinkers.versionwatch.platform.curseforge.ConfigCurseForge;
import io.github.milkdrinkers.versionwatch.platform.curseforge.ConfigCurseForgeBuilder;
import io.github.milkdrinkers.versionwatch.platform.curseforge.CurseForgeCheck;
import io.github.milkdrinkers.versionwatch.platform.exception.ConfigException;
import io.github.milkdrinkers.versionwatch.platform.github.ConfigGithub;
import io.github.milkdrinkers.versionwatch.platform.github.ConfigGithubBuilder;
import io.github.milkdrinkers.versionwatch.platform.github.GithubCheck;
import io.github.milkdrinkers.versionwatch.platform.hangar.ConfigHangar;
import io.github.milkdrinkers.versionwatch.platform.hangar.ConfigHangarBuilder;
import io.github.milkdrinkers.versionwatch.platform.hangar.HangarCheck;
import io.github.milkdrinkers.versionwatch.platform.modrinth.ConfigModrinth;
import io.github.milkdrinkers.versionwatch.platform.modrinth.ConfigModrinthBuilder;
import io.github.milkdrinkers.versionwatch.platform.modrinth.ModrinthCheck;
import io.github.milkdrinkers.versionwatch.platform.polymart.ConfigPolymart;
import io.github.milkdrinkers.versionwatch.platform.polymart.ConfigPolymartBuilder;
import io.github.milkdrinkers.versionwatch.platform.polymart.PolymartCheck;
import io.github.milkdrinkers.versionwatch.platform.spigot.ConfigSpigot;
import io.github.milkdrinkers.versionwatch.platform.spigot.ConfigSpigotBuilder;
import io.github.milkdrinkers.versionwatch.platform.spigot.SpigotCheck;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public class VersionWatcher {
    private final @NotNull Platform platform;
    private final @NotNull PlatformImplementation platformImplementation;
    private final @NotNull Version currentVersion;
    private volatile @Nullable Version latestVersion;

    @ApiStatus.Internal
    VersionWatcher(@NotNull Platform platform, @NotNull PlatformImplementation platformImplementation, @NotNull Version currentVersion) {
        this(platform, platformImplementation, currentVersion, null);
    }

    @ApiStatus.Internal
    VersionWatcher(@NotNull Platform platform, @NotNull PlatformImplementation platformImplementation, @NotNull Version currentVersion, @Nullable Version latestVersion) {
        this.platform = platform;
        this.platformImplementation = platformImplementation;
        this.currentVersion = currentVersion;
        this.latestVersion = latestVersion;
    }

    /**
     * Get the current {@link Platform} this version watcher is using.
     *
     * @return the platform
     * @see PlatformImplementation#getPlatform()
     */
    public @NotNull Platform getPlatform() {
        return platform;
    }

    /**
     * Get the current {@link PlatformImplementation} this version watcher is using.
     *
     * @return platform implementation
     * @see BuiltByBitCheck
     * @see CurseForgeCheck
     * @see GithubCheck
     * @see HangarCheck
     * @see ModrinthCheck
     * @see PolymartCheck
     * @see SpigotCheck
     */
    public @NotNull PlatformImplementation getPlatformImplementation() {
        return platformImplementation;
    }

    /**
     * Get the download url of the latest version.
     *
     * @return url
     */
    public @NotNull String getDownloadURL() {
        return getPlatformImplementation().getConfig().getLatestReleaseLink();
    }

    /**
     * Get the currently running version.
     *
     * @return current version
     * @see #getLatestVersion()
     * @see #isLatest()
     */
    public @NotNull Version getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Get the latest version or null if it has not been fetched yet.
     *
     * @return latest version or null
     * @see #getCurrentVersion()
     * @see #isLatest()
     * @see #fetchLatest()
     * @see #fetchLatestAsync()
     */
    public @Nullable Version getLatestVersion() {
        return latestVersion;
    }

    /**
     * Checks if the current version is newer or equal than the latest release
     *
     * @return boolean
     * @see Version#isNewerOrEqual(Version, Version)
     */
    public boolean isLatest() {
        if (getLatestVersion() == null)
            return true;

        return Version.isNewerOrEqual(getCurrentVersion(), getLatestVersion());
    }

    /**
     * Fetch the latest version from the configured {@link PlatformImplementation}.
     *
     * @return the latest version or null
     * @see PlatformImplementation
     */
    public @Nullable Version fetchLatest() {
        try {
            setLatestVersion(getPlatformImplementation().fetchLatestVersion());
        } catch (Throwable ignored) {
            setLatestVersion(null);
        }
        return latestVersion;
    }

    /**
     * Fetch the latest version from the configured {@link PlatformImplementation}.
     *
     * @return a completable future with the latest version or null
     * @see PlatformImplementation
     */
    public @NotNull CompletableFuture<@Nullable Version> fetchLatestAsync() {
        return getPlatformImplementation()
            .fetchLatestVersionAsync()
            .thenApply(version -> {
                setLatestVersion(version);
                return version;
            });
    }

    /**
     * Internally used to guarantee safe access to the latestVersion field
     *
     * @param version latest version or null
     */
    @ApiStatus.Internal
    private synchronized void setLatestVersion(final @Nullable Version version) {
        latestVersion = version;
    }

    /**
     * A builder that builds a {@link VersionWatcher}.
     *
     * @return builder
     * @see VersionWatcher
     */
    public static @NotNull Builder builder() {
        return new Builder();
    }

    /**
     * A builder that builds a {@link VersionWatcher}.
     * </br></br>This builder uses the individual {@link PlatformConfigBuilder}'s and instantiates the configured {@link PlatformImplementation} for use by the {@link VersionWatcher}.
     *
     * @see VersionWatcher
     */
    public static class Builder {
        private @Nullable Platform platform;
        private @Nullable PlatformImplementation platformImplementation;
        private @Nullable Version currentVersion;

        /**
         * Set what platform implementation you wish to use.
         *
         * @param platform platform
         * @see Platform
         */
        @NotNull
        public Builder withPlatform(@Nullable Platform platform) {
            this.platform = platform;
            return this;
        }

        /**
         * Provide your own {@link PlatformImplementation}.
         *
         * @param platformImplementation platform implementation
         * @apiNote This make the builder skip creation of a new {@link PlatformImplementation} in favor of the one you provide here.
         * @see PlatformImplementation
         */
        @NotNull
        public Builder withPlatformImplementation(@Nullable PlatformImplementation platformImplementation) {
            this.platformImplementation = platformImplementation;
            return this;
        }

        /**
         * The current version of this software.
         *
         * @param currentVersion the current version
         * @see Version
         * @see Version#of(String)
         * @see Version#of(long, long, long, String, String)
         */
        @NotNull
        public Builder withVersion(@Nullable Version currentVersion) {
            this.currentVersion = currentVersion;
            return this;
        }

        // Identification

        private @Nullable String resourceId;

        /**
         * The resource/project/repository id.
         *
         * @param id resource/project/repository id
         * @apiNote For usage with {@link Platform#BuiltByBit}, {@link Platform#CurseForge}, {@link Platform#Hangar}, {@link Platform#Modrinth}, {@link Platform#Polymart}, {@link Platform#Spigot}.
         * @see #withResourceSlug(String)
         */
        @NotNull
        public Builder withResourceId(@Nullable String id) {
            this.resourceId = id;
            return this;
        }

        private @Nullable String resourceSlug;

        /**
         * The resource/project/repository slug.
         *
         * @param slug resource/project/repository slug
         * @apiNote For usage with {@link Platform#GitHub}, {@link Platform#CurseForge}, {@link Platform#Hangar}, {@link Platform#Modrinth}.
         * @see #withResourceId(String)
         */
        @NotNull
        public Builder withResourceSlug(@Nullable String slug) {
            this.resourceSlug = slug;
            return this;
        }

        private @Nullable String resourceOwner;

        /**
         * The resource/project/repository owner.
         *
         * @param owner resource/project/repository slug
         * @apiNote For usage with {@link Platform#GitHub}, {@link Platform#Hangar}.
         * @see #withResourceId(String)
         * @see #withResourceSlug(String)
         */
        @NotNull
        public Builder withResourceOwner(@Nullable String owner) {
            this.resourceOwner = owner;
            return this;
        }

        private @NotNull String userAgent = "VersionWatch";

        /**
         * The user agent used by VersionWatch. It is recommended to set this to the name of your software.
         *
         * @param userAgent the user agent
         */
        @NotNull
        public Builder withAgent(@NotNull String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        // Auth

        private @Nullable String token; // Only used with the builtbybit platform
        private @Nullable TokenType tokenType; // Only used with the builtbybit platform

        /**
         * This is only required/used when using the {@link Platform#BuiltByBit} platform, as it is required to authenticate with the BuiltByBit API.</br></br>
         * See <a href="https://builtbybit.com/wiki/ultimate-api/">BuiltByBit API Documentation</a> for how to set authentication up.
         *
         * @param token token
         * @apiNote Only for usage with BuiltByBit!
         * @see BuiltByBitCheck
         * @see ConfigBuiltByBit
         * @see ConfigBuiltByBitBuilder
         */
        @NotNull
        public Builder withToken(@Nullable String token) {
            this.token = token;
            return this;
        }

        /**
         * This is only required/used when using the {@link Platform#BuiltByBit} platform, as it is required to authenticate with the BuiltByBit API.</br></br>
         * See <a href="https://builtbybit.com/wiki/ultimate-api/">BuiltByBit API Documentation</a> for how to set authentication up.
         *
         * @param tokenType token type
         * @apiNote Only for usage with BuiltByBit!
         * @see BuiltByBitCheck
         * @see ConfigBuiltByBit
         * @see ConfigBuiltByBitBuilder
         */
        @NotNull
        public Builder withTokenType(@Nullable TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        private @Nullable String apiKey; // Only used with the curseforge platform

        /**
         * This is only required/used when using the {@link Platform#CurseForge} platform, as it is required to authenticate with the CurseForge API.</br></br>
         * See <a href="https://console.curseforge.com/#/api-keys">CurseForge API Key Documentation</a> for how to set one up.
         *
         * @param apiKey key
         * @apiNote Only for usage with CurseForge!
         * @see CurseForgeCheck
         * @see ConfigCurseForge
         * @see ConfigCurseForgeBuilder
         */
        @NotNull
        public Builder withApiKey(@Nullable String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Builds the {@link VersionWatcher}.
         *
         * @return version watcher
         * @throws ConfigException if fields are wrong/missing for the requested implementation
         */
        @NotNull
        public VersionWatcher build() throws ConfigException {
            if (currentVersion == null)
                throw new ConfigException("Tried to build without providing current version!");

            // Build using platform and provided data
            if (platformImplementation == null && platform != null) {
                switch (platform) {
                    case BuiltByBit:
                        final ConfigBuiltByBit config1 = new ConfigBuiltByBitBuilder()
                            .withResourceId(resourceId)
                            .withUserAgent(userAgent)
                            .withToken(token)
                            .withTokenType(tokenType)
                            .build();

                        platformImplementation = new BuiltByBitCheck(config1);
                        break;
                    case CurseForge:
                        final ConfigCurseForge config2 = new ConfigCurseForgeBuilder()
                            .withProjectId(resourceId)
                            .withProjectSlug(resourceSlug)
                            .withUserAgent(userAgent)
                            .withApiKey(apiKey)
                            .build();

                        platformImplementation = new CurseForgeCheck(config2);
                        break;
                    case GitHub:
                        final ConfigGithub config3 = new ConfigGithubBuilder()
                            .withRepo(resourceSlug)
                            .withOwner(resourceOwner)
                            .withUserAgent(userAgent)
                            .build();

                        platformImplementation = new GithubCheck(config3);
                        break;
                    case Hangar:
                        final ConfigHangar config7 = new ConfigHangarBuilder()
                            .withProjectId(resourceId)
                            .withProjectSlug(resourceSlug)
                            .withOwner(resourceOwner)
                            .withUserAgent(userAgent)
                            .build();

                        platformImplementation = new HangarCheck(config7);
                        break;
                    case Modrinth:
                        final ConfigModrinth config4 = new ConfigModrinthBuilder()
                            .withProjectId(resourceId)
                            .withProjectSlug(resourceSlug)
                            .withUserAgent(userAgent)
                            .build();

                        platformImplementation = new ModrinthCheck(config4);
                        break;
                    case Polymart:
                        final ConfigPolymart config5 = new ConfigPolymartBuilder()
                            .withResourceId(resourceId)
                            .withUserAgent(userAgent)
                            .build();

                        platformImplementation = new PolymartCheck(config5);
                        break;
                    case Spigot:
                        final ConfigSpigot config6 = new ConfigSpigotBuilder()
                            .withResourceId(resourceId)
                            .withUserAgent(userAgent)
                            .build();

                        platformImplementation = new SpigotCheck(config6);
                        break;
                }
            }

            // Check platform implementation
            if (platformImplementation == null)
                throw new ConfigException("Tried to build but platformImplementation is null!");

            // Get platform if not provided
            if (platform == null)
                platform = platformImplementation.getPlatform();

            return new VersionWatcher(platform, platformImplementation, currentVersion);
        }
    }
}
