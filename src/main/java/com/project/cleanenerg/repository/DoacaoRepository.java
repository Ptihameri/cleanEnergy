package com.project.cleanenerg.repository;

import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findByProjeto(Projeto projeto);

    List<Doacao> findByUsuario(Usuario usuario);
}
