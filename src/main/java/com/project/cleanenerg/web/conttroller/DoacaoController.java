package com.project.cleanenerg.web.conttroller;

import com.project.cleanenerg.exception.ProjetoNotFoundException;
import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.repository.DoacaoRepository;
import com.project.cleanenerg.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {
    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @PostMapping
    public ResponseEntity<Doacao> fazerDoacao(@RequestBody Doacao doacao) throws ProjetoNotFoundException {
        Projeto projeto = projetoService.atualizarValorArrecadado(doacao.getProjeto().getId(), doacao.getValor());
        Doacao novaDoacao = doacaoRepository.save(doacao);
        return new ResponseEntity<>(novaDoacao, HttpStatus.CREATED);
    }

}
