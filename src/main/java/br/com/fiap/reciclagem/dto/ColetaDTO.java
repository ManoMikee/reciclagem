package br.com.fiap.reciclagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ColetaDTO {

    private Long id;

    @NotNull(message = "A data da coleta é obrigatória")
    private LocalDateTime dataColeta;

    @NotNull(message = "A quantidade da coleta é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private BigDecimal quantidadeKg;

    @NotBlank(message = "O responsável pela coleta é obrigatório")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private String responsavel;

    @NotNull(message = "O ponto de coleta é obrigatório")
    @Positive(message = "O ID do ponto de coleta deve ser maior que zero")
    private Long pontoColetaId;

    public ColetaDTO() {
    }

    public ColetaDTO(Long id, LocalDateTime dataColeta,
                     BigDecimal quantidadeKg, String responsavel,
                     Long pontoColetaId) {
        this.id = id;
        this.dataColeta = dataColeta;
        this.quantidadeKg = quantidadeKg;
        this.responsavel = responsavel;
        this.pontoColetaId = pontoColetaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public BigDecimal getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(BigDecimal quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Long getPontoColetaId() {
        return pontoColetaId;
    }

    public void setPontoColetaId(Long pontoColetaId) {
        this.pontoColetaId = pontoColetaId;
    }
}