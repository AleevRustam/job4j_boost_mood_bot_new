package ru.job4j.exceptions;

public class SentContentException extends RuntimeException {
    public SentContentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
