package br.com.fiap.reciclagem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositoDTO {

    private Long id;

    @NotNull(message = "A quantidade do depósito é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private BigDecimal quantidadeKg;

    @NotNull(message = "A data do depósito é obrigatória")
    private LocalDateTime dataDeposito;

    @NotNull(message = "O ponto de coleta é obrigatório")
    @Positive(message = "O ID do ponto de coleta deve ser maior que zero")
    private Long pontoColetaId;

    public DepositoDTO() {
    }

    public DepositoDTO(Long id, BigDecimal quantidadeKg,
                       LocalDateTime dataDeposito, Long pontoColetaId) {
        this.id = id;
        this.quantidadeKg = quantidadeKg;
        this.dataDeposito = dataDeposito;
        this.pontoColetaId = pontoColetaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(BigDecimal quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public LocalDateTime getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(LocalDateTime dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public Long getPontoColetaId() {
        return pontoColetaId;
    }

    public void setPontoColetaId(Long pontoColetaId) {
        this.pontoColetaId = pontoColetaId;
    }
}