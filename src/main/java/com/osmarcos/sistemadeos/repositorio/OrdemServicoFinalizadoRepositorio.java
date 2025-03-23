package com.osmarcos.sistemadeos.repositorio;

import com.osmarcos.sistemadeos.entidades.OrdemServicoFinalizado;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdemServicoFinalizadoRepositorio extends JpaRepository<OrdemServicoFinalizado, Long> {

    // Filtra por nome do colaborador
    List<OrdemServicoFinalizado> findByColaborador(String colaborador);

    // Filtra por data de finalização
    List<OrdemServicoFinalizado> findByDataFinalizado(LocalDate dataFinalizado);

    // Filtra por nome do colaborador e data de finalização
    List<OrdemServicoFinalizado> findByColaboradorAndDataFinalizado(String colaborador, LocalDate dataFinalizado);
}
