package com.project.cleanenerg.web.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String username;
    @NotBlank
    @Email(message = "Email inválido")
    private String email;
    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;
}
