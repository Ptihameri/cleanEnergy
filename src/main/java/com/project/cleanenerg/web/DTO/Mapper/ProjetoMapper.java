package com.project.cleanenerg.web.DTO.Mapper;

import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.web.DTO.ProjetoCreateDTO;
import com.project.cleanenerg.web.DTO.ProjetoDTO;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

public class ProjetoMapper {

    public static ProjetoDTO toDTO(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();
        dto.setId(projeto.getId());
        dto.setNome(projeto.getNome());
        dto.setDescricao(projeto.getDescricao());
        dto.setValorMeta(projeto.getValorMeta());
        dto.setValorArrecadado(projeto.getValorArrecadado());
        return dto;
    }

    public static Projeto toEntity(ProjetoDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setId(dto.getId());
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setValorMeta(dto.getValorMeta());
        projeto.setValorArrecadado(dto.getValorArrecadado());
        return projeto;
    }

    public static Projeto toProjeto(ProjetoCreateDTO dto) {
        return new ModelMapper().map(dto, Projeto.class);


    }

    public static List<ProjetoDTO> toListDto(List<Projeto> usuarios) {
        return usuarios.stream().map(user -> toDTO(user)).collect(Collectors.toList());
    }
}