package com.project.cleanenerg.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String senha) {
        super(senha);
    }
}
