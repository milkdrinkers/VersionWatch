package io.github.milkdrinkers.versionwatch.platform.exception;

public class BadResponseException extends VersionWatchException {
    private static final long serialVersionUID = -172212006094236068L;

    public BadResponseException(String message) {
        super(message);
    }

    public BadResponseException(String message, Exception e) {
        super(message, e);
    }
}
