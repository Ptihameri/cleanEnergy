package com.project.cleanenerg.service;

import ch.qos.logback.core.net.SyslogOutputStream;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProjetoRepository projetoRepository;
    private final ProjetoService projetoService;


    @Transactional
    public Doacao salvarDoacao(DoacaoCreateDTO doacao) {
        System.out.println(doacao.getProjeto());

        // Verifica se o projeto existe
        Projeto projeto = projetoRepository.findById(doacao.getProjeto())
                .orElseThrow(() -> new NaoEmcontradoException("nao achei"));

        Usuario usuario = usuarioRepository.findById(doacao.getUsuario())
                .orElseThrow(() -> new NaoEmcontradoException("nao achei"));

        // Atualiza o valor arrecadado do projeto
        projeto.setValorArrecadado(projeto.getValorArrecadado() + doacao.getValor());

        projetoService.atualizarProjeto(doacao.getProjeto(), projeto);

        // Transforma retorno em doacao

        DoacaoMapper doacaoMapper = new DoacaoMapper();
        // Salva a doação
        Doacao save = doacaoRepository.save(doacaoMapper.toDoacao(doacao,projeto,usuario));
        return save;
    }

    @Transactional(readOnly = true)
    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Doacao> listarDoacoesPorProjeto(Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNotFoundException());
        return doacaoRepository.findByProjeto(projeto);
    }

    @Transactional(readOnly = true)
    public Doacao obterDoacaoPorId(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEmcontradoException("Doação não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<Doacao> listarDoacaoPorUsuario(Long userId){
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow( () -> new NaoEmcontradoException("Doação nao encontrada para o usuario indormado"));
        return doacaoRepository.findByUsuario(usuario);
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
