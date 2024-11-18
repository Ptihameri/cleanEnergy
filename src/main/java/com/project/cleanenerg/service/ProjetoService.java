package com.project.cleanenerg.service;

import com.project.cleanenerg.exception.ProjetoNotFoundException;
import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.repository.DoacaoRepository;
import com.project.cleanenerg.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;



    @Transactional
    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @Transactional
    public Projeto atualizarValorArrecadado(Long projetoId, Double valorDoacao) throws ProjetoNotFoundException {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNotFoundException());
        projeto.setValorArrecadado(projeto.getValorArrecadado() + valorDoacao);
        return projetoRepository.save(projeto);
    }
    @Transactional(readOnly = true)
    public Projeto obterProjeto(Long id) throws ProjetoNotFoundException {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ProjetoNotFoundException());
    }
    @Transactional(readOnly = true)
    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    @Transactional
    public Projeto atualizarProjeto(Long id, Projeto projetoAtualizado) throws ProjetoNotFoundException {
        return projetoRepository.findById(id)
                .map(projeto -> {
                    projeto.setNome(projetoAtualizado.getNome() == null ? projeto.getNome() : projetoAtualizado.getNome());
                    projeto.setDescricao(projetoAtualizado.getDescricao() != null? projetoAtualizado.getDescricao() : projeto.getDescricao());
                    projeto.setValorArrecadado(projetoAtualizado.getValorArrecadado() != null ? projetoAtualizado.getValorArrecadado() : projeto.getValorArrecadado());
                    projeto.setValorMeta(projetoAtualizado.getValorMeta() != null ? projetoAtualizado.getValorMeta() : projeto.getValorMeta());
                    return projetoRepository.save(projeto);
                })
                .orElseThrow(() -> new ProjetoNotFoundException());
    }


    @Transactional
    public void deletarProjeto(Long id) throws ProjetoNotFoundException {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ProjetoNotFoundException());
        projetoRepository.delete(projeto);
    }
}
