package com.project.cleanenerg.repository;

import com.project.cleanenerg.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
