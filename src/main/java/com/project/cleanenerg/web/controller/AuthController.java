package com.project.cleanenerg.web.controller;

import com.project.cleanenerg.Jwt.JwtToken;
import com.project.cleanenerg.service.UsuarioDetailsService;
import com.project.cleanenerg.web.DTO.UsuarioLoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final UsuarioDetailsService datailsService;
    private final AuthenticationManager autenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDTO dto, HttpServletRequest request) {
        log.info("Autenticando usuario: {}", dto.getUsername());
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

            autenticationManager.authenticate(authenticationToken);

            JwtToken token = datailsService.getJwtTokenAcesso(dto.getUsername());

            return ResponseEntity.ok(token);

        } catch (AuthenticationException ex) {
            log.error("Erro de autenticação par o usuario: {}", ex.getMessage());
        }
        return ResponseEntity
                .badRequest()
                .body("Credenciais invalidas a001");
    }

}
