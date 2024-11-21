package com.project.cleanenerg.web.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjetoCreateDTO {

    @NotBlank(message = "O nome do projeto não pode ser vazio.")
    private String nome;
    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;
    @NotNull(message = "O valor da meta não pode ser nulo.")
    @Positive(message = "O valor da meta deve ser positivo.")
    private Double valorMeta;
    private Double valorArrecadado;
    private String imagem;

}
