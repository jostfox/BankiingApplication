package org.example.exceptions;

public class NotEmptyBalanceException extends RuntimeException{
    public NotEmptyBalanceException(String message) {
        super(message);
    }
}
