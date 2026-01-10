package net.thewesthill.wps;

public class WpsApiException extends RuntimeException {

    public WpsApiException(String message) {
        super(message);
    }

    public WpsApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
