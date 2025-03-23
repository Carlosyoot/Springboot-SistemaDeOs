package com.osmarcos.sistemadeos.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;



@Entity
public class Marcas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da marca obrigatório")
    @Column(unique = true)
    private String nome;


/////////////////////////////////////////GETTERS E SETTERS ///////////////////////////////////////

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
