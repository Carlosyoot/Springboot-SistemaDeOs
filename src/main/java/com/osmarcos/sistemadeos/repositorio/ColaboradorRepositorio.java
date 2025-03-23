package com.osmarcos.sistemadeos.repositorio;

import com.osmarcos.sistemadeos.entidades.Colaborador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ColaboradorRepositorio extends JpaRepository<Colaborador, Long> {

    boolean existsByNome(String nome);

    @Query("SELECT c.nome FROM Colaborador c")
    public List<String> findAllNomes();
}
