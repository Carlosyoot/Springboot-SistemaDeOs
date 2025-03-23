package com.osmarcos.sistemadeos.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;




@Entity
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, message = "O nome deve ter no minímo 3 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    private String email;


    @Null
    private String telefone; 

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6,message = "A senha deve ter no mínimo 6 dígitos")
    private String senha;       







/////////////////////////////////////////////GETTERS E SETTERS//////////////////////////////

    public Long getId(){
        return id;
    }
    
    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
}
