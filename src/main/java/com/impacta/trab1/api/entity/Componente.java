package com.impacta.trab1.api.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_componentes")
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "peca_codigo", referencedColumnName = "codigo")
    private Peca peca;

    // construtores
    public Componente() {
        super();
    }

    public Componente(Long id, String sku, String descricao, Double preco, Integer quantidade, Peca peca) {
        this.id = id;
        this.sku = sku;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.peca = peca;
    }

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    //equals e hashcode
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Componente that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSku(), that.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSku());
    }

    //toString
    @Override
    public String toString() {
        return "Componente{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", peca=" + peca +
                '}';
    }
}

