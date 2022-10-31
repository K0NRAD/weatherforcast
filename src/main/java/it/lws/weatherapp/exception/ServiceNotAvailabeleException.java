package it.lws.weatherapp.exception;

public class ServiceNotAvailabeleException extends RuntimeException {
    public ServiceNotAvailabeleException(String message) {
        super(message);
    }
}
