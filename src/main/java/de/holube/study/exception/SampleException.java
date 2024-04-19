package de.holube.study.exception;

public class SampleException extends Exception {

    public SampleException() {

    }

    public SampleException(String message) {
        super(message);
    }

    public SampleException(String message, Throwable cause) {
        super(message, cause);
    }

}