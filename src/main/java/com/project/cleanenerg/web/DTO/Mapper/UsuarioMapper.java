package com.project.cleanenerg.web.DTO.Mapper;

import com.project.cleanenerg.entities.Usuario;
import com.project.cleanenerg.web.DTO.UsuarioCreateDTO;
import com.project.cleanenerg.web.DTO.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDTO dto) {
        return new ModelMapper().map(dto, Usuario.class);


    }
    public static UsuarioResponseDTO toDto(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
