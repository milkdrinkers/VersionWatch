package io.github.milkdrinkers.versionwatch.platform.exception;

public class BadStatusCodeException extends VersionWatchException {
    public BadStatusCodeException(String message) {
        super(message);
    }

    public BadStatusCodeException(String message, Exception e) {
        super(message, e);
    }
}