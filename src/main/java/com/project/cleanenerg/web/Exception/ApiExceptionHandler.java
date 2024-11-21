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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

        private ResponseEntity<ErrorResponse> buildErrorResponse(Exception ex, HttpServletRequest request, HttpStatus status) {
            log.error("Api erro:", ex);
            ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage(), request.getRequestURI());
            return ResponseEntity.status(status)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
            return buildErrorResponse(ex, request, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> outroErroException(Exception ex, HttpServletRequest request) {
            return buildErrorResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
            return buildErrorResponse(ex, request, HttpStatus.FORBIDDEN);
        }

        @ExceptionHandler(PasswordInvalidException.class)
        public ResponseEntity<ErrorResponse> passwordInvalidException(PasswordInvalidException ex, HttpServletRequest request) {
            return buildErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(NaoEmcontradoException.class)
        public ResponseEntity<ErrorResponse> naoEmcontradoException(NaoEmcontradoException ex, HttpServletRequest request) {
            return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler({UsernameEmailException.class, ProjetoNotFoundException.class})
        public ResponseEntity<ErrorResponse> handleProjetoNotFoundException(Exception ex, HttpServletRequest request) {
            return buildErrorResponse(ex, request, HttpStatus.NOT_FOUND);

    }

}