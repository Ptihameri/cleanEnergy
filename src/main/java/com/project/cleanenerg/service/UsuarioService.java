package com.project.cleanenerg.service;

import com.project.cleanenerg.entities.Usuario;
import com.project.cleanenerg.exception.NaoEmcontradoException;
import com.project.cleanenerg.exception.PasswordInvalidException;
import com.project.cleanenerg.exception.UsernameEmailException;
import com.project.cleanenerg.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new UsernameEmailException(String.format("Email %s já cadastrado", usuario.getUsername()));
        }

    }

    //TODO: Verificar bloqueio de usuario
    @Transactional(readOnly = true)
    public Usuario buscarId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(
                () -> new NaoEmcontradoException("Usuario não encontrado")
        );
    }

    @Transactional
    public Usuario editarSenha(Long id, String password, String novaSenha, String confirmacaoSenha) {
        if (!novaSenha.equals(confirmacaoSenha)) {
            throw new PasswordInvalidException("Nova senha e confirmação de senha não conferem");
        }
        Usuario user = buscarId(id);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordInvalidException("Senha não Confere");
        }
        if (user.getPassword().equals(novaSenha)) {
            throw new PasswordInvalidException("Nova senha não pode ser igual a senha atual");
        }
        user.setPassword(passwordEncoder.encode(novaSenha));

        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new NaoEmcontradoException(String.format("Usuario '%s' não encontrado", username))
        );
    }
    @Transactional(readOnly = true)
    public Usuario.ROLE buscarRolePeloUsuario(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
