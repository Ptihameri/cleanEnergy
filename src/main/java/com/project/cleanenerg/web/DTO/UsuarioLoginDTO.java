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
public class UsuarioLoginDTO {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, max = 6, message = "A senha deve ter 6 caracteres")
    private String password;
}
