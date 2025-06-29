package com.impacta.trab1.api.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_pecas")
public class Peca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long codigo;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "peca", cascade = CascadeType.ALL)
    private List<Componente> componentes = new ArrayList<>();

    //construtores
    public Peca() {
        super();
    }

    public Peca(Long id, Long codigo, String nome, List<Componente> componentes) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.componentes = componentes;
    }

    //getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<Componente> componentes) {
        this.componentes = componentes;
    }

    //equals e hashcode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Peca peca = (Peca) o;
        return Objects.equals(id, peca.id) && Objects.equals(codigo, peca.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }

    //toString
    @Override
    public String toString() {
        return "Peca{" +
                "id=" + id +
                ", codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", componentes=" + componentes +
                '}';
    }
}
