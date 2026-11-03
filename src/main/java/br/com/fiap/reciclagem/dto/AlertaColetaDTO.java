package br.com.fiap.reciclagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AlertaColetaDTO {

    private Long id;

    @NotNull(message = "A data do alerta é obrigatória")
    private LocalDateTime dataAlerta;

    @NotNull(message = "A quantidade do alerta é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private BigDecimal quantidadeKg;

    @NotBlank(message = "O status do alerta é obrigatório")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres")
    private String status;

    @NotNull(message = "O ponto de coleta é obrigatório")
    @Positive(message = "O ID do ponto de coleta deve ser maior que zero")
    private Long pontoColetaId;

    public AlertaColetaDTO() {
    }

    public AlertaColetaDTO(Long id, LocalDateTime dataAlerta,
                           BigDecimal quantidadeKg, String status,
                           Long pontoColetaId) {
        this.id = id;
        this.dataAlerta = dataAlerta;
        this.quantidadeKg = quantidadeKg;
        this.status = status;
        this.pontoColetaId = pontoColetaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAlerta() {
        return dataAlerta;
    }

    public void setDataAlerta(LocalDateTime dataAlerta) {
        this.dataAlerta = dataAlerta;
    }

    public BigDecimal getQuantidadeKg() {
        return quantidadeKg;
    }

    public void setQuantidadeKg(BigDecimal quantidadeKg) {
        this.quantidadeKg = quantidadeKg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPontoColetaId() {
        return pontoColetaId;
    }

    public void setPontoColetaId(Long pontoColetaId) {
        this.pontoColetaId = pontoColetaId;
    }
}