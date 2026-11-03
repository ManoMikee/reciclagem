package br.com.fiap.reciclagem.controller;

import br.com.fiap.reciclagem.dto.ColetaDTO;
import br.com.fiap.reciclagem.model.Coleta;
import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.service.ColetaService;
import br.com.fiap.reciclagem.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/coletas")
public class ColetaController {

    private final ColetaService coletaService;
    private final PontoColetaService pontoColetaService;

    public ColetaController(
            ColetaService coletaService,
            PontoColetaService pontoColetaService) {

        this.coletaService = coletaService;
        this.pontoColetaService = pontoColetaService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<ColetaDTO> listarTodos() {

        return coletaService.listarTodos()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ColetaDTO buscarPorId(@PathVariable Long id) {

        return converterParaDTO(buscarEntidadePorId(id));
    }

    // CADASTRAR
    @PostMapping
    public ResponseEntity<ColetaDTO> cadastrar(
            @Valid @RequestBody ColetaDTO dto) {

        PontoColeta pontoColeta = buscarPontoColeta(dto.getPontoColetaId());

        Coleta coleta = new Coleta(
                dto.getDataColeta(),
                dto.getQuantidadeKg(),
                dto.getResponsavel(),
                pontoColeta
        );

        Coleta salva = coletaService.salvar(coleta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converterParaDTO(salva));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ColetaDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ColetaDTO dto) {

        Coleta coleta = buscarEntidadePorId(id);
        PontoColeta pontoColeta = buscarPontoColeta(dto.getPontoColetaId());

        coleta.setDataColeta(dto.getDataColeta());
        coleta.setQuantidadeKg(dto.getQuantidadeKg());
        coleta.setResponsavel(dto.getResponsavel());
        coleta.setPontoColeta(pontoColeta);

        Coleta atualizada = coletaService.salvar(coleta);

        return converterParaDTO(atualizada);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        buscarEntidadePorId(id);

        coletaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // BUSCAR COLETA
    private Coleta buscarEntidadePorId(Long id) {

        Coleta coleta = coletaService.buscarPorId(id);

        if (coleta == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Coleta não encontrada."
            );
        }

        return coleta;
    }

    // BUSCAR PONTO DE COLETA
    private PontoColeta buscarPontoColeta(Long id) {

        PontoColeta pontoColeta = pontoColetaService.buscarPorId(id);

        if (pontoColeta == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ponto de coleta não encontrado."
            );
        }

        return pontoColeta;
    }

    // CONVERTER ENTITY PARA DTO
    private ColetaDTO converterParaDTO(Coleta coleta) {

        return new ColetaDTO(
                coleta.getId(),
                coleta.getDataColeta(),
                coleta.getQuantidadeKg(),
                coleta.getResponsavel(),
                coleta.getPontoColeta().getId()
        );
    }
}