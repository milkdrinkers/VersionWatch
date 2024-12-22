package io.github.milkdrinkers.versionwatch.platform.exception;

public class VersionWatchException extends RuntimeException {
    public VersionWatchException(String message) {
        super(message);
    }

    public VersionWatchException(String message, Exception e) {
        super(message, e);
    }
}
