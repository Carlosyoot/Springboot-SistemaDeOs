package com.osmarcos.sistemadeos.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate dataAbertura;
    private LocalDate dataFinalizado;
    private String status;


    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;









/////////////////////////////////////////GETTERS E SETTERS ///////////////////////////////////////
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public LocalDate getDataFinalizado(){
        return dataFinalizado;
    }

    public void setDataFinalizado(LocalDate dataFinalizado){
        this.dataFinalizado = dataFinalizado;
    }


    
}
