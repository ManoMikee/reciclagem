package br.com.fiap.reciclagem.controller;

import br.com.fiap.reciclagem.dto.TipoMaterialDTO;
import br.com.fiap.reciclagem.model.TipoMaterial;
import br.com.fiap.reciclagem.service.TipoMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-materiais")
public class TipoMaterialController {

    private final TipoMaterialService tipoMaterialService;

    public TipoMaterialController(TipoMaterialService tipoMaterialService) {
        this.tipoMaterialService = tipoMaterialService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<TipoMaterialDTO> listarTodos() {

        return tipoMaterialService.listarTodos()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public TipoMaterialDTO buscarPorId(@PathVariable Long id) {

        TipoMaterial tipoMaterial = buscarEntidadePorId(id);

        return converterParaDTO(tipoMaterial);
    }

    // CADASTRAR
    @PostMapping
    public ResponseEntity<TipoMaterialDTO> cadastrar(
            @Valid @RequestBody TipoMaterialDTO dto) {

        TipoMaterial tipoMaterial = new TipoMaterial(
                dto.getNome(),
                dto.getDescricao()
        );

        TipoMaterial salvo = tipoMaterialService.salvar(tipoMaterial);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converterParaDTO(salvo));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public TipoMaterialDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoMaterialDTO dto) {

        TipoMaterial tipoMaterial = buscarEntidadePorId(id);

        tipoMaterial.setNome(dto.getNome());
        tipoMaterial.setDescricao(dto.getDescricao());

        TipoMaterial atualizado = tipoMaterialService.salvar(tipoMaterial);

        return converterParaDTO(atualizado);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        buscarEntidadePorId(id);

        tipoMaterialService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // BUSCAR ENTIDADE OU RETORNAR ERRO 404
    private TipoMaterial buscarEntidadePorId(Long id) {

        TipoMaterial tipoMaterial = tipoMaterialService.buscarPorId(id);

        if (tipoMaterial == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tipo de material não encontrado."
            );
        }

        return tipoMaterial;
    }

    // CONVERTER ENTITY PARA DTO
    private TipoMaterialDTO converterParaDTO(TipoMaterial tipoMaterial) {

        return new TipoMaterialDTO(
                tipoMaterial.getId(),
                tipoMaterial.getNome(),
                tipoMaterial.getDescricao()
        );
    }
}