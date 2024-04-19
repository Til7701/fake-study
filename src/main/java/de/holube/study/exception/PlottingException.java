package de.holube.study.exception;

public class PlottingException extends Exception {

    public PlottingException() {

    }

    public PlottingException(String message) {
        super(message);
    }

    public PlottingException(String message, Throwable cause) {
        super(message, cause);
    }

}
