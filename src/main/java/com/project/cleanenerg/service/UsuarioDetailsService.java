package com.project.cleanenerg.service;

import com.project.cleanenerg.Jwt.JwtDetalhesDoUsuario;
import com.project.cleanenerg.Jwt.JwtToken;
import com.project.cleanenerg.Jwt.JwtUtils;
import com.project.cleanenerg.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorUsername(username);
        return new JwtDetalhesDoUsuario(usuario);
    }
    public JwtToken getJwtTokenAcesso(String username){
        Usuario.ROLE role = usuarioService.buscarRolePeloUsuario(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
