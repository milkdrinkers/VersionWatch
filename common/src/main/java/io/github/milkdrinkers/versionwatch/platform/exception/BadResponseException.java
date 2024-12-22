package io.github.milkdrinkers.versionwatch.platform.exception;

public class BadResponseException extends VersionWatchException {
    public BadResponseException(String message) {
        super(message);
    }

    public BadResponseException(String message, Exception e) {
        super(message, e);
    }
}
