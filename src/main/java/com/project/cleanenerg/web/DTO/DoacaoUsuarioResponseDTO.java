package com.project.cleanenerg.web.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoacaoUsuarioResponseDTO {

    private Double valor;

    private String username;

    private Long projetoId;

    private Long idDoacao;
}