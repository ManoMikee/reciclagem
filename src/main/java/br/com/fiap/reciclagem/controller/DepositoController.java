package br.com.fiap.reciclagem.controller;

import br.com.fiap.reciclagem.dto.DepositoDTO;
import br.com.fiap.reciclagem.model.Deposito;
import br.com.fiap.reciclagem.model.PontoColeta;
import br.com.fiap.reciclagem.service.DepositoService;
import br.com.fiap.reciclagem.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/depositos")
public class DepositoController {

    private final DepositoService depositoService;
    private final PontoColetaService pontoColetaService;

    public DepositoController(
            DepositoService depositoService,
            PontoColetaService pontoColetaService) {

        this.depositoService = depositoService;
        this.pontoColetaService = pontoColetaService;
    }

    // LISTAR TODOS
    @GetMapping
    public List<DepositoDTO> listarTodos() {

        return depositoService.listarTodos()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public DepositoDTO buscarPorId(@PathVariable Long id) {

        return converterParaDTO(buscarEntidadePorId(id));
    }

    // CADASTRAR
    @PostMapping
    public ResponseEntity<DepositoDTO> cadastrar(
            @Valid @RequestBody DepositoDTO dto) {

        PontoColeta pontoColeta = buscarPontoColeta(dto.getPontoColetaId());

        Deposito deposito = new Deposito(
                dto.getQuantidadeKg(),
                dto.getDataDeposito(),
                pontoColeta
        );

        Deposito salvo = depositoService.salvar(deposito);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converterParaDTO(salvo));
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public DepositoDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DepositoDTO dto) {

        Deposito deposito = buscarEntidadePorId(id);
        PontoColeta pontoColeta = buscarPontoColeta(dto.getPontoColetaId());

        deposito.setQuantidadeKg(dto.getQuantidadeKg());
        deposito.setDataDeposito(dto.getDataDeposito());
        deposito.setPontoColeta(pontoColeta);

        Deposito atualizado = depositoService.salvar(deposito);

        return converterParaDTO(atualizado);
    }

    // EXCLUIR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        buscarEntidadePorId(id);

        depositoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    // BUSCAR DEPÓSITO
    private Deposito buscarEntidadePorId(Long id) {

        Deposito deposito = depositoService.buscarPorId(id);

        if (deposito == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Depósito não encontrado."
            );
        }

        return deposito;
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
    private DepositoDTO converterParaDTO(Deposito deposito) {

        return new DepositoDTO(
                deposito.getId(),
                deposito.getQuantidadeKg(),
                deposito.getDataDeposito(),
                deposito.getPontoColeta().getId()
        );
    }
}