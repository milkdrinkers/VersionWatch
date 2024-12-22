package io.github.milkdrinkers.versionwatch.platform.exception;

public class ConfigException extends VersionWatchException {
    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Exception e) {
        super(message, e);
    }
}
