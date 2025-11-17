package io.github.milkdrinkers.versionwatch.platform.exception;

public class VersionWatchException extends RuntimeException {
    private static final long serialVersionUID = -1834782112500763456L;

    public VersionWatchException(String message) {
        super(message);
    }

    public VersionWatchException(String message, Exception e) {
        super(message, e);
    }
}
