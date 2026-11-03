package br.com.fiap.reciclagem.controller;

import br.com.fiap.reciclagem.dto.PontoColetaDTO;
import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.model.TipoMaterial;
import br.com.fiap.reciclagem.service.PontoColetaService;
import br.com.fiap.reciclagem.service.TipoMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pontos-coleta")
public class PontoColetaController {

    private final PontoColetaService pontoColetaService;
    private final TipoMaterialService tipoMaterialService;

    public PontoColetaController(
            PontoColetaService pontoColetaService,
            TipoMaterialService tipoMaterialService) {

        this.pontoColetaService = pontoColetaService;
        this.tipoMaterialService = tipoMaterialService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<PontoColetaDTO> listarTodos() {

        return pontoColetaService.listarTodos()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public PontoColetaDTO buscarPorId(@PathVariable Long id) {

        return converterParaDTO(buscarEntidadePorId(id));
    }

    // CADASTRAR
    @PostMapping
    public ResponseEntity<PontoColetaDTO> cadastrar(
            @Valid @RequestBody PontoColetaDTO dto) {

        TipoMaterial tipoMaterial = buscarTipoMaterial(dto.getTipoMaterialId());

        PontoColeta pontoColeta = new PontoColeta(
                dto.getNome(),
                dto.getLocalizacao(),
                dto.getCapacidadeKg(),
                dto.getLimiteAlertaKg(),
                dto.getQuantidadeAtualKg(),
                dto.getStatus(),
                tipoMaterial
        );

        PontoColeta salvo = pontoColetaService.salvar(pontoColeta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converterParaDTO(salvo));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public PontoColetaDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PontoColetaDTO dto) {

        PontoColeta pontoColeta = buscarEntidadePorId(id);
        TipoMaterial tipoMaterial = buscarTipoMaterial(dto.getTipoMaterialId());

        pontoColeta.setNome(dto.getNome());
        pontoColeta.setLocalizacao(dto.getLocalizacao());
        pontoColeta.setCapacidadeKg(dto.getCapacidadeKg());
        pontoColeta.setLimiteAlertaKg(dto.getLimiteAlertaKg());
        pontoColeta.setQuantidadeAtualKg(dto.getQuantidadeAtualKg());
        pontoColeta.setStatus(dto.getStatus());
        pontoColeta.setTipoMaterial(tipoMaterial);

        PontoColeta atualizado = pontoColetaService.salvar(pontoColeta);

        return converterParaDTO(atualizado);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        buscarEntidadePorId(id);

        pontoColetaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // BUSCAR PONTO DE COLETA
    private PontoColeta buscarEntidadePorId(Long id) {

        PontoColeta pontoColeta = pontoColetaService.buscarPorId(id);

        if (pontoColeta == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ponto de coleta não encontrado."
            );
        }

        return pontoColeta;
    }

    // BUSCAR TIPO DE MATERIAL
    private TipoMaterial buscarTipoMaterial(Long id) {

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
    private PontoColetaDTO converterParaDTO(PontoColeta pontoColeta) {

        return new PontoColetaDTO(
                pontoColeta.getId(),
                pontoColeta.getNome(),
                pontoColeta.getLocalizacao(),
                pontoColeta.getCapacidadeKg(),
                pontoColeta.getLimiteAlertaKg(),
                pontoColeta.getQuantidadeAtualKg(),
                pontoColeta.getStatus(),
                pontoColeta.getTipoMaterial().getId()
        );
    }
}