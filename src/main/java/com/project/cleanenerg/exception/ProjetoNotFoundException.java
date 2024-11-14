package com.project.cleanenerg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProjetoNotFoundException extends RuntimeException {

    public ProjetoNotFoundException() {
        super("Projeto não encontrado");
    }
}