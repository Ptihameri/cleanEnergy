package com.project.cleanenerg.repository;

import com.project.cleanenerg.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.entities.Projeto;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    // Método que retorna uma página de doações por projeto
    Page<Doacao> findByProjeto(Projeto projeto, Pageable pageable);

    // Método que retorna uma página de doações por usuário
    Page<Doacao> findByUsuario(Usuario usuario, Pageable pageable);

    // Método que retorna uma página de todas as doações
    Page<Doacao> findAll(Pageable pageable);
}
