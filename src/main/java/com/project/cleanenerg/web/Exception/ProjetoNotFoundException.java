package com.project.cleanenerg.web.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProjetoNotFoundException extends RuntimeException {
    @ExceptionHandler(ProjetoNotFoundException.class)
    public ResponseEntity<String> handleProjetoNotFoundException(ProjetoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    public ProjetoNotFoundException() {
        super("Projeto não encontrado");
    }
}