package com.project.cleanenerg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UsernameEmailException extends RuntimeException {

    public UsernameEmailException(String mensagem) {
        super(mensagem);
    }
}
