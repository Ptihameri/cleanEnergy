package com.project.cleanenerg.web.DTO.Mapper;

import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.web.DTO.ProjetoCreateDTO;
import com.project.cleanenerg.web.DTO.ProjetoResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProjetoMapper {

    public static ProjetoResponseDTO toDTO(Projeto projeto) {
        ProjetoResponseDTO dto = new ProjetoResponseDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setValorMeta(projeto.getValorMeta());
        dto.setValorArrecadado(projeto.getValorArrecadado());
        dto.setImagem(projeto.getImagem());
        return dto;
    }

    public static Projeto toProjeto(ProjetoCreateDTO dto) {
        return new ModelMapper().map(dto, Projeto.class);


    }

    public static List<ProjetoResponseDTO> toListDto(List<Projeto> projetos) {
        return projetos.stream().map(projeto -> toDTO(projeto)).collect(Collectors.toList());    }
}