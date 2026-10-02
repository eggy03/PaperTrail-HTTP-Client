package io.github.eggy03.papertrail.sdk.exception;

public class PaperTrailFatalException extends RuntimeException {
    public PaperTrailFatalException(String message) {
        super(message);
    }

    public PaperTrailFatalException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
