package com.osmarcos.sistemadeos.repositorio;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.osmarcos.sistemadeos.entidades.Marcas;


public interface MarcasRepositorio extends JpaRepository<Marcas,Long> {

    boolean existsByNome(String nome);
    Optional<Marcas> findByNome(String nome);



    @Query("SELECT m.nome FROM Marcas m")
    public List<String> findAllNomes();
    
}
