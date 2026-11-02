package br.com.fiap.reciclagem.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "COLETA")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COLETA")
    private Long id;

    @Column(name = "DATA_COLETA", nullable = false)
    private LocalDateTime dataColeta;

    @Column(name = "QUANTIDADE_KG", nullable = false)
    private BigDecimal quantidadeKg;

    @Column(name = "RESPONSAVEL", nullable = false, length = 100)
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "ID_PONTO", nullable = false)
    private PontoColeta pontoColeta;

    public Coleta() {
    }

    public Coleta(LocalDateTime dataColeta, BigDecimal quantidadeKg,
                  String responsavel, PontoColeta pontoColeta) {
        this.dataColeta = dataColeta;
        this.quantidadeKg = quantidadeKg;
        this.responsavel = responsavel;
        this.pontoColeta = pontoColeta;
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

    public PontoColeta getPontoColeta() {
        return pontoColeta;
    }

    public void setPontoColeta(PontoColeta pontoColeta) {
        this.pontoColeta = pontoColeta;
    }
}