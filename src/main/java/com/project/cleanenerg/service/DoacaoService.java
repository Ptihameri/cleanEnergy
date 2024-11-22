package com.project.cleanenerg.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.entities.Usuario;
import com.project.cleanenerg.exception.NaoEmcontradoException;
import com.project.cleanenerg.exception.ProjetoNotFoundException;
import com.project.cleanenerg.repository.DoacaoRepository;
import com.project.cleanenerg.repository.ProjetoRepository;
import com.project.cleanenerg.repository.UsuarioRepository;
import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;
import com.project.cleanenerg.web.DTO.Mapper.DoacaoMapper;

@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProjetoRepository projetoRepository;
    private final ProjetoService projetoService;

    public DoacaoService(DoacaoRepository doacaoRepository, UsuarioRepository usuarioRepository,
                         ProjetoRepository projetoRepository, ProjetoService projetoService) {
        this.doacaoRepository = doacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.projetoRepository = projetoRepository;
        this.projetoService = projetoService;
    }

    @Transactional
    public Doacao salvarDoacao(DoacaoCreateDTO doacao) {
        Projeto projeto = projetoRepository.findById(doacao.getProjeto())
                .orElseThrow(() -> new NaoEmcontradoException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(doacao.getUsuario())
                .orElseThrow(() -> new NaoEmcontradoException("Usuário não encontrado"));

        // Atualiza o valor arrecadado do projeto
        projeto.setValorArrecadado(projeto.getValorArrecadado() + doacao.getValor());
        projetoService.atualizarProjeto(doacao.getProjeto(), projeto);

        // Salva a doação
        DoacaoMapper doacaoMapper = new DoacaoMapper();
        return doacaoRepository.save(doacaoMapper.toDoacao(doacao, projeto, usuario));
    }

    @Transactional(readOnly = true)
    public Page<Doacao> listarDoacoesPaginadas(Pageable pageable) {
        return doacaoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Doacao> listarDoacoesPorProjeto(Long projetoId, Pageable pageable) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNotFoundException());
        return doacaoRepository.findByProjeto(projeto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Doacao> listarDoacoesPorUsuario(Long usuarioId, Pageable pageable) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NaoEmcontradoException("Usuário não encontrado"));
        return doacaoRepository.findByUsuario(usuario, pageable);
    }

    @Transactional(readOnly = true)
    public Doacao obterDoacaoPorId(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEmcontradoException("Doação não encontrada"));
    }

    @Transactional
    public void deletarDoacao(Long id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEmcontradoException("Doação não encontrada"));

        // Atualiza o valor arrecadado no projeto ao deletar uma doação
        Projeto projeto = doacao.getProjeto();
        if (projeto != null) {
            projeto.setValorArrecadado(projeto.getValorArrecadado() - doacao.getValor());
            projetoRepository.save(projeto);
        }

        doacaoRepository.delete(doacao);
    }
}
