package br.com.fiap.reciclagem.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ALERTA_COLETA")
public class AlertaColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALERTA")
    private Long id;

    @Column(name = "DATA_ALERTA", nullable = false)
    private LocalDateTime dataAlerta;

    @Column(name = "QUANTIDADE_KG", nullable = false)
    private BigDecimal quantidadeKg;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "ID_PONTO", nullable = false)
    private PontoColeta pontoColeta;

    public AlertaColeta() {
    }

    public AlertaColeta(LocalDateTime dataAlerta, BigDecimal quantidadeKg, String status, PontoColeta pontoColeta) {
        this.dataAlerta = dataAlerta;
        this.quantidadeKg = quantidadeKg;
        this.status = status;
        this.pontoColeta = pontoColeta;
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

    public PontoColeta getPontoColeta() {
        return pontoColeta;
    }

    public void setPontoColeta(PontoColeta pontoColeta) {
        this.pontoColeta = pontoColeta;
    }
}