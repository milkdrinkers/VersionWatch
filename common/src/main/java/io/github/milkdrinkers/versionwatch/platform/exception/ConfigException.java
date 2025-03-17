package io.github.milkdrinkers.versionwatch.platform.exception;

@SuppressWarnings("unused")
public class ConfigException extends VersionWatchException {
    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Exception e) {
        super(message, e);
    }
}
