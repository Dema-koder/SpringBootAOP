package com.example.SpringBootAOP.exception;

import com.example.SpringBootAOP.annotation.Throw;

@Throw
public class ApplicationException extends RuntimeException {
    public ApplicationException() {}

    public ApplicationException(String message) {
        super(message);
    }
}
