package com.project.cleanenerg.web.controller;

import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.service.DoacaoService;
import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;
import com.project.cleanenerg.web.DTO.DoacaoResponseDTO;
import com.project.cleanenerg.web.DTO.DoacaoUsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.cleanenerg.web.DTO.Mapper.DoacaoMapper.toListDto;
import static com.project.cleanenerg.web.DTO.Mapper.DoacaoMapper.toResponseDto;

@RestController
@RequestMapping("/doacoes") // Define o endpoint base para as doações
public class DoacaoController {

    private final DoacaoService doacaoService;

    @Autowired
    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    // Endpoint para criar uma nova doação
    @PostMapping
    public ResponseEntity<DoacaoResponseDTO> fazerDoacao(@RequestBody DoacaoCreateDTO doacao) {
        Doacao novaDoacao = doacaoService.salvarDoacao(doacao);
        return ResponseEntity.ok(toResponseDto(novaDoacao));
    }

    // Endpoint para listar todas as doações
    @GetMapping
    public ResponseEntity<List<DoacaoUsuarioResponseDTO>> listarTodasDoacoes() {
        List<Doacao> doacoes = doacaoService.listarDoacoes();
        return ResponseEntity.ok(toListDto(doacoes));
    }

    // Endpoint para listar doações de um projeto específico
    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<Doacao>> listarDoacoesPorProjeto(@PathVariable Long projetoId) {
        List<Doacao> doacoes = doacaoService.listarDoacoesPorProjeto(projetoId);
        return ResponseEntity.ok(doacoes);
    }

    // Endpoint para obter uma doação pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> obterDoacaoPorId(@PathVariable Long id) {
        Doacao doacao = doacaoService.obterDoacaoPorId(id);
        return ResponseEntity.ok(doacao);
    }

    // Endpoint para listar doações de um usuário específico
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<DoacaoUsuarioResponseDTO>> listarDoacaoPorUsuario(@PathVariable Long userId) {
        List<Doacao> doacoes = doacaoService.listarDoacaoPorUsuario(userId);
        return ResponseEntity.ok(toListDto(doacoes));
    }

    // Endpoint para deletar uma doação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable Long id) {
        doacaoService.deletarDoacao(id);
        return ResponseEntity.noContent().build();
    }


//        @Autowired
//        private ProjetoService projetoService;
//
//        @Autowired
//        private DoacaoRepository doacaoRepository;
//
//        @PostMapping
//        public ResponseEntity<Doacao> fazerDoacao(@RequestBody Doacao doacao) throws ProjetoNotFoundException {
//            Projeto projeto = projetoService.atualizarValorArrecadado(doacao.getProjeto().getId(), doacao.getValor());
//            Doacao novaDoacao = doacaoRepository.save(doacao);
//            return new ResponseEntity<>(novaDoacao, HttpStatus.CREATED);
//        }


}
