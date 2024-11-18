package com.project.cleanenerg.web.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjetoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double valorMeta;
    private Double valorArrecadado;
    private String imagem;



}
