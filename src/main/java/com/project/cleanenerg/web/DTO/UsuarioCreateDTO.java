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
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String nome;
    @NotBlank
    @Size(min = 5, max = 25, message = "O username deve ter entre 5 e 50 caracteres.")
    private String username;
    @NotBlank
    @Email(message = "Email inválido")
    private String email;
    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
}
