package com.osmarcos.sistemadeos.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrdemServicoFinalizado {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "O produto é obrigatório")
    private String produto;

    @NotNull(message = "A data de abertura é obrigatória")
    private LocalDate dataAbertura;

    @NotNull(message = "A data de finalização é obrigatória")
    private LocalDate dataFinalizado;

    @NotBlank(message = "O colaborador é obrigatório")
    private String colaborador;

    @NotBlank(message = "O status é obrigatório")
    private String status;






////////////////////////////////////////////////////GETTERS E SETTERS////////////////////////////


    public Long getid(){
        return id;
    }

    public void setid(Long id){
        this.id = id;
    }

    public String getProduto(){
        return produto;
    }

    public void setProduto(String produto){
        this.produto = produto;
    }

    public LocalDate getDataAbertura(){
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura){
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFinalizado(){
        return dataFinalizado;
    }

    public void setDataFinalizado(LocalDate dataFinalizado){
        this.dataFinalizado = dataFinalizado;
    }

    public String getColaborador(){
        return colaborador;
    }

    public void setColaborador(String colaborador){
        this.colaborador = colaborador;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
