package io.github.milkdrinkers.versionwatch.platform.exception;

@SuppressWarnings("unused")
public class ConfigException extends VersionWatchException {
    private static final long serialVersionUID = -6545594957608094968L;

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Exception e) {
        super(message, e);
    }
}
