package io.github.milkdrinkers.versionwatch.platform.exception;

@SuppressWarnings("unused")
public class BadStatusCodeException extends VersionWatchException {
    private static final long serialVersionUID = 5833020775016129469L;

    public BadStatusCodeException(String message) {
        super(message);
    }

    public BadStatusCodeException(String message, Exception e) {
        super(message, e);
    }
}
