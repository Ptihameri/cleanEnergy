package com.project.cleanenerg.web.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoacaoUsuarioResponseDTO {

    private Double valor;

    private Long usuarioId;

    private Long projetoId;

    private LocalDate dataDoacao;

    private Long idDoacao;
}