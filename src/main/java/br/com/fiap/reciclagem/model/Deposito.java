package br.com.fiap.reciclagem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DEPOSITO")
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEPOSITO")
    private Long id;

    @Column(name = "QUANTIDADE_KG", nullable = false)
    private BigDecimal quantidadeKg;

    @Column(name = "DATA_DEPOSITO", nullable = false)
    private LocalDateTime dataDeposito;

    @ManyToOne
    @JoinColumn(name = "ID_PONTO", nullable = false)
    private PontoColeta pontoColeta;

    public Deposito() {
    }

    public Deposito(BigDecimal quantidadeKg, LocalDateTime dataDeposito, PontoColeta pontoColeta) {
        this.quantidadeKg = quantidadeKg;
        this.dataDeposito = dataDeposito;
        this.pontoColeta = pontoColeta;
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

    public PontoColeta getPontoColeta() {
        return pontoColeta;
    }

    public void setPontoColeta(PontoColeta pontoColeta) {
        this.pontoColeta = pontoColeta;
    }
}
