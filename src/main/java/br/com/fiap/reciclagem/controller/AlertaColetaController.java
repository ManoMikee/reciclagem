package br.com.fiap.reciclagem.controller;

import br.com.fiap.reciclagem.dto.AlertaColetaDTO;
import br.com.fiap.reciclagem.model.AlertaColeta;
import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.service.AlertaColetaService;
import br.com.fiap.reciclagem.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/alertas-coleta")
public class AlertaColetaController {

    private final AlertaColetaService alertaColetaService;
    private final PontoColetaService pontoColetaService;

    public AlertaColetaController(
            AlertaColetaService alertaColetaService,
            PontoColetaService pontoColetaService) {

        this.alertaColetaService = alertaColetaService;
        this.pontoColetaService = pontoColetaService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<AlertaColetaDTO> listarTodos() {

        return alertaColetaService.listarTodos()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public AlertaColetaDTO buscarPorId(@PathVariable Long id) {

        return converterParaDTO(buscarEntidadePorId(id));
    }

    // CADASTRAR
    @PostMapping
    public ResponseEntity<AlertaColetaDTO> cadastrar(
            @Valid @RequestBody AlertaColetaDTO dto) {

        PontoColeta pontoColeta = buscarPontoColeta(dto.getPontoColetaId());

        AlertaColeta alertaColeta = new AlertaColeta(
                dto.getDataAlerta(),
                dto.getQuantidadeKg(),
                dto.getStatus(),
                pontoColeta
        );

        AlertaColeta salvo = alertaColetaService.salvar(alertaColeta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converterParaDTO(salvo));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public AlertaColetaDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlertaColetaDTO dto) {

        AlertaColeta alertaColeta = buscarEntidadePorId(id);
        PontoColeta pontoColeta = buscarPontoColeta(dto.getPontoColetaId());

        alertaColeta.setDataAlerta(dto.getDataAlerta());
        alertaColeta.setQuantidadeKg(dto.getQuantidadeKg());
        alertaColeta.setStatus(dto.getStatus());
        alertaColeta.setPontoColeta(pontoColeta);

        AlertaColeta atualizado = alertaColetaService.salvar(alertaColeta);

        return converterParaDTO(atualizado);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        buscarEntidadePorId(id);

        alertaColetaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // BUSCAR ALERTA
    private AlertaColeta buscarEntidadePorId(Long id) {

        AlertaColeta alertaColeta = alertaColetaService.buscarPorId(id);

        if (alertaColeta == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Alerta de coleta não encontrado."
            );
        }

        return alertaColeta;
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
    private AlertaColetaDTO converterParaDTO(AlertaColeta alertaColeta) {

        return new AlertaColetaDTO(
                alertaColeta.getId(),
                alertaColeta.getDataAlerta(),
                alertaColeta.getQuantidadeKg(),
                alertaColeta.getStatus(),
                alertaColeta.getPontoColeta().getId()
        );
    }
}