package br.com.fiap.reciclagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class PontoColetaDTO {

    private Long id;

    @NotBlank(message = "O nome do ponto de coleta é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "A localização é obrigatória")
    @Size(max = 150, message = "A localização deve ter no máximo 150 caracteres")
    private String localizacao;

    @NotNull(message = "A capacidade é obrigatória")
    @Positive(message = "A capacidade deve ser maior que zero")
    private BigDecimal capacidadeKg;

    @NotNull(message = "O limite de alerta é obrigatório")
    @Positive(message = "O limite de alerta deve ser maior que zero")
    private BigDecimal limiteAlertaKg;

    @NotNull(message = "A quantidade atual é obrigatória")
    @PositiveOrZero(message = "A quantidade atual não pode ser negativa")
    private BigDecimal quantidadeAtualKg;

    @NotBlank(message = "O status é obrigatório")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres")
    private String status;

    @NotNull(message = "O tipo de material é obrigatório")
    @Positive(message = "O ID do tipo de material deve ser maior que zero")
    private Long tipoMaterialId;

    public PontoColetaDTO() {
    }

    public PontoColetaDTO(Long id, String nome, String localizacao,
                          BigDecimal capacidadeKg, BigDecimal limiteAlertaKg,
                          BigDecimal quantidadeAtualKg, String status,
                          Long tipoMaterialId) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidadeKg = capacidadeKg;
        this.limiteAlertaKg = limiteAlertaKg;
        this.quantidadeAtualKg = quantidadeAtualKg;
        this.status = status;
        this.tipoMaterialId = tipoMaterialId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public BigDecimal getCapacidadeKg() {
        return capacidadeKg;
    }

    public void setCapacidadeKg(BigDecimal capacidadeKg) {
        this.capacidadeKg = capacidadeKg;
    }

    public BigDecimal getLimiteAlertaKg() {
        return limiteAlertaKg;
    }

    public void setLimiteAlertaKg(BigDecimal limiteAlertaKg) {
        this.limiteAlertaKg = limiteAlertaKg;
    }

    public BigDecimal getQuantidadeAtualKg() {
        return quantidadeAtualKg;
    }

    public void setQuantidadeAtualKg(BigDecimal quantidadeAtualKg) {
        this.quantidadeAtualKg = quantidadeAtualKg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTipoMaterialId() {
        return tipoMaterialId;
    }

    public void setTipoMaterialId(Long tipoMaterialId) {
        this.tipoMaterialId = tipoMaterialId;
    }
}