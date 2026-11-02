package br.com.fiap.reciclagem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PONTO_COLETA")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PONTO")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "LOCALIZACAO", nullable = false, length = 150)
    private String localizacao;

    @Column(name = "CAPACIDADE_KG", nullable = false)
    private BigDecimal capacidadeKg;

    @Column(name = "LIMITE_ALERTA_KG", nullable = false)
    private BigDecimal limiteAlertaKg;

    @Column(name = "QUANTIDADE_ATUAL_KG", nullable = false)
    private BigDecimal quantidadeAtualKg;

    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_MATERIAL", nullable = false)
    private TipoMaterial tipoMaterial;

    public PontoColeta() {
    }

    public PontoColeta(String nome, String localizacao, BigDecimal capacidadeKg, BigDecimal limiteAlertaKg, BigDecimal quantidadeAtualKg, String status, TipoMaterial tipoMaterial) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidadeKg = capacidadeKg;
        this.limiteAlertaKg = limiteAlertaKg;
        this.quantidadeAtualKg = quantidadeAtualKg;
        this.status = status;
        this.tipoMaterial = tipoMaterial;
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

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
}
