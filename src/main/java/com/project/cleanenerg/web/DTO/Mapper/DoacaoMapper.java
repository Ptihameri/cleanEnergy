package com.project.cleanenerg.web.DTO.Mapper;

import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.entities.Usuario;
import com.project.cleanenerg.repository.ProjetoRepository;
import com.project.cleanenerg.repository.UsuarioRepository;
import com.project.cleanenerg.service.UsuarioService;
import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;
import com.project.cleanenerg.web.DTO.DoacaoResponseDTO;
import com.project.cleanenerg.web.DTO.DoacaoUsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DoacaoMapper {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProjetoRepository projetoRepository;


    // Map DTO to Entity, manually mapping Usuario and Projeto
    public static Doacao toDoacao(DoacaoCreateDTO dto, Projeto projeto, Usuario usuario) {
        // Convert basic properties using ModelMapper
        Doacao doacao = new ModelMapper().map(dto, Doacao.class);
        doacao.setUsuario(usuario);
        doacao.setProjeto(projeto);

        return doacao;
    }

    public static DoacaoUsuarioResponseDTO toRespondeUsuarioDto(Doacao doacao) {
        DoacaoUsuarioResponseDTO doacaoResponse = new ModelMapper().map(doacao, DoacaoUsuarioResponseDTO.class);
        doacaoResponse.setUsername(doacao.getUsuario().getUsername());
        return doacaoResponse;
    }

    public static List<DoacaoUsuarioResponseDTO> toListDto(List<Doacao> doacaos) {
        return doacaos.stream().map(doacao -> toRespondeUsuarioDto(doacao)).collect(Collectors.toList());
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
