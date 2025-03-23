package com.osmarcos.sistemadeos.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osmarcos.sistemadeos.entidades.Marcas;


public interface MarcasRepositorio extends JpaRepository<Marcas,Long> {

    boolean existsByNome(String nome);


    @Query("SELECT m.nome FROM Marcas m")
    public List<String> findAllNomes();
    
}
