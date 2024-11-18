package com.project.cleanenerg.web.DTO.Mapper;

import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.entities.Usuario;
import com.project.cleanenerg.repository.ProjetoRepository;
import com.project.cleanenerg.repository.UsuarioRepository;
import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;
import com.project.cleanenerg.web.DTO.DoacaoResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
public class DoacaoMapper {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;


    // Map DTO to Entity, manually mapping Usuario and Projeto
    public static Doacao toDoacao(DoacaoCreateDTO dto,Projeto projeto,Usuario usuario) {
        // Convert basic properties using ModelMapper
        Doacao doacao = new ModelMapper().map(dto, Doacao.class);
        doacao.setUsuario(usuario);
        doacao.setProjeto(projeto);

        return doacao;
    }

    // Map Doacao Entity to Response DTO
    public static DoacaoResponseDTO toResponseDto(Doacao doacao) {
        return new ModelMapper().map(doacao, DoacaoResponseDTO.class);
    }

    // Map Doacao Entity to Create DTO (without Usuario and Projeto)
    public static DoacaoCreateDTO toCreateDto(Doacao doacao) {
        return new ModelMapper().map(doacao, DoacaoCreateDTO.class);
    }
}
