package com.project.cleanenerg.web.controller;

import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.exception.ProjetoNotFoundException;
import com.project.cleanenerg.service.ProjetoService;
import com.project.cleanenerg.web.DTO.Mapper.ProjetoMapper;
import com.project.cleanenerg.web.DTO.ProjetoCreateDTO;
import com.project.cleanenerg.web.DTO.ProjetoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.cleanenerg.web.DTO.Mapper.ProjetoMapper.toDTO;
import static com.project.cleanenerg.web.DTO.Mapper.ProjetoMapper.toListDto;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criarProjeto(@Valid @RequestBody ProjetoCreateDTO dto) {
        // Converte o DTO de criação para a entidade Projeto
        Projeto novoProjeto = ProjetoMapper.toProjeto(dto);

        // Salva o projeto usando o serviço
        ProjetoResponseDTO projetoSalvo = toDTO(projetoService.salvarProjeto(novoProjeto));

        // Converte a entidade salva para o DTO de resposta e retorna
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) throws ProjetoNotFoundException {
        Projeto projeto = projetoService.atualizarProjeto(id, projetoAtualizado);
        return ResponseEntity.ok(toDTO(projeto));
    }


    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarProjetos();
        return new ResponseEntity<List<ProjetoResponseDTO>>(toListDto(projetos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> obterProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
        Projeto projeto = projetoService.obterProjeto(id);
        return ResponseEntity.ok(toDTO(projeto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
        projetoService.deletarProjeto(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
