package de.holube.fakestudy.exception;

public class CalculationException extends Exception {

    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }

}
