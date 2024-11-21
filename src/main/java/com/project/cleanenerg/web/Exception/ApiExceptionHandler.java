package com.project.cleanenerg.web.Exception;

import com.project.cleanenerg.exception.NaoEmcontradoException;
import com.project.cleanenerg.exception.PasswordInvalidException;
import com.project.cleanenerg.exception.ProjetoNotFoundException;
import com.project.cleanenerg.exception.UsernameEmailException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({UsernameEmailException.class, ProjetoNotFoundException.class})
    public ResponseEntity<String> handleProjetoNotFoundException(ProjetoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NaoEmcontradoException.class)
    public ResponseEntity<String> naoEmcontradoException(RuntimeException ex, HttpServletRequest request) {
        log.error("Api erro:", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<String> passwordInvalidException(RuntimeException ex, HttpServletRequest request) {
        log.error("Api erro:", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.error("Api erro:", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> outroErroException(Exception ex, HttpServletRequest request) {
        log.error("Api erro interno: {} {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).
                contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result) {
        log.error("Api erro:", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).
                contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }
}
