package com.project.cleanenerg.web.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoacaoCreateDTO {

        @NotNull(message = "O valor da doação não pode ser nulo")
        @Positive(message = "O valor da doação deve ser positivo")
        private Double valor;

        @NotNull(message = "O usuário não pode ser nulo")
        private Long usuario; // Alterado para Long para armazenar o ID do usuário

        @NotNull(message = "O projeto não pode ser nulo")
        private Long projeto; // Alterado para Long para armazenar o ID do projeto
}
