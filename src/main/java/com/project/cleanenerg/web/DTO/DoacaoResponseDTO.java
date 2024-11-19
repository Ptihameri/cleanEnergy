package com.project.cleanenerg.web.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoacaoResponseDTO {
    @NotNull(message = "O valor da doação não pode ser nulo")
    @Positive(message = "O valor da doação deve ser positivo")
    private Double valor;

    @NotNull(message = "O usuário não pode ser nulo")
    private Long usuarioId;

    @NotNull(message = "O projeto não pode ser nulo")
    private Long projetoId;

    @NotNull(message = "A data da doação não pode ser nula")
    private LocalDate dataDoacao = LocalDate.now();

    private Long id;
}