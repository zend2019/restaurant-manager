package main.java.common.exceptions;

public class RestaurantManagerException extends RuntimeException {
    public RestaurantManagerException() {
    }

    public RestaurantManagerException(String message) {
        super(message);
    }

    public RestaurantManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantManagerException(Throwable cause) {
        super(cause);
    }
}
