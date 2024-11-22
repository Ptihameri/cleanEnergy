package com.project.cleanenerg.web.controller;

import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.service.DoacaoService;
import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;
import com.project.cleanenerg.web.DTO.DoacaoResponseDTO;
import com.project.cleanenerg.web.DTO.DoacaoUsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // Endpoint para listar todas as doações com paginação
    @GetMapping
    public ResponseEntity<?> listarTodasDoacoes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); // Cria o Pageable com os parâmetros da requisição
        Page<Doacao> doacoesPage = doacaoService.listarDoacoesPaginadas(pageable);

        // Convertendo a página de doações para DTOs
        List<DoacaoUsuarioResponseDTO> doacoesDTO = toListDto(doacoesPage.getContent());

        // Montando a resposta com informações de paginação
        return ResponseEntity.ok(new PaginatedResponse<>(
                doacoesDTO,
                doacoesPage.getNumber(),
                doacoesPage.getSize(),
                doacoesPage.getTotalElements(),
                doacoesPage.getTotalPages()
        ));
    }

    // Endpoint para listar doações de um projeto específico com paginação
    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<?> listarDoacoesPorProjeto(
            @PathVariable Long projetoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); // Cria o Pageable com os parâmetros da requisição
        Page<Doacao> doacoesPage = doacaoService.listarDoacoesPorProjeto(projetoId, pageable);

        // Convertendo a página de doações para DTOs
        List<DoacaoUsuarioResponseDTO> doacoesDTO = toListDto(doacoesPage.getContent());

        // Montando a resposta com informações de paginação
        return ResponseEntity.ok(new PaginatedResponse<>(
                doacoesDTO,
                doacoesPage.getNumber(),
                doacoesPage.getSize(),
                doacoesPage.getTotalElements(),
                doacoesPage.getTotalPages()
        ));
    }

    // Endpoint para listar doações de um usuário específico com paginação
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> listarDoacaoPorUsuario(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size); // Cria o Pageable com os parâmetros da requisição
        Page<Doacao> doacoesPage = doacaoService.listarDoacoesPorUsuario(userId, pageable);

        // Convertendo a página de doações para DTOs
        List<DoacaoUsuarioResponseDTO> doacoesDTO = toListDto(doacoesPage.getContent());

        // Montando a resposta com informações de paginação
        return ResponseEntity.ok(new PaginatedResponse<>(
                doacoesDTO,
                doacoesPage.getNumber(),
                doacoesPage.getSize(),
                doacoesPage.getTotalElements(),
                doacoesPage.getTotalPages()
        ));
    }

    // Endpoint para obter uma doação pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> obterDoacaoPorId(@PathVariable Long id) {
        Doacao doacao = doacaoService.obterDoacaoPorId(id);
        return ResponseEntity.ok(doacao);
    }

    // Endpoint para deletar uma doação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable Long id) {
        doacaoService.deletarDoacao(id);
        return ResponseEntity.noContent().build();
    }

    // Classe para representar a resposta paginada
    public static class PaginatedResponse<T> {
        private List<T> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;

        public PaginatedResponse(List<T> content, int page, int size, long totalElements, int totalPages) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }

        public List<T> getContent() {
            return content;
        }

        public int getPage() {
            return page;
        }

        public int getSize() {
            return size;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }
    }
}
