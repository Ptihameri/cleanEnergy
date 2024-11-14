package com.project.cleanenerg.web.conttroller;

import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.exception.ProjetoNotFoundException;
import com.project.cleanenerg.service.ProjetoService;
import com.project.cleanenerg.web.DTO.Mapper.ProjetoMapper;
import com.project.cleanenerg.web.DTO.ProjetoCreateDTO;
import com.project.cleanenerg.web.DTO.ProjetoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.cleanenerg.web.DTO.Mapper.ProjetoMapper.*;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<ProjetoDTO> criarProjeto(@Valid @RequestBody ProjetoCreateDTO dto) {
        // Converte o DTO de criação para a entidade Projeto
        Projeto novoProjeto = ProjetoMapper.toProjeto(dto);

        // Salva o projeto usando o serviço
        Projeto projetoSalvo = projetoService.salvarProjeto(novoProjeto);

        // Converte a entidade salva para o DTO de resposta e retorna
        return ResponseEntity.status(HttpStatus.CREATED).body(ProjetoMapper.toDTO(projetoSalvo));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) throws ProjetoNotFoundException {
        Projeto projeto = projetoService.atualizarProjeto(id, projetoAtualizado);
        return ResponseEntity.ok(toDTO(projeto));
    }

//    @GetMapping("/projetos")
//    public Page<Projeto> listarProjetos(Pageable pageable) {
//        return projetoRepository.findAll(pageable);
//    }

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarProjetos();
        return new ResponseEntity<List<ProjetoDTO>>(toListDto(projetos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> obterProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
        Projeto projeto = projetoService.obterProjeto(id);
        return ResponseEntity.ok(toDTO(projeto));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
        projetoService.deletarProjeto(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
