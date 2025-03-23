package com.osmarcos.sistemadeos.repositorio;

import com.osmarcos.sistemadeos.entidades.OrdemServicoFinalizado;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdemServicoFinalizadoRepositorio extends JpaRepository<OrdemServicoFinalizado, Long> {

    List<OrdemServicoFinalizado> findByColaborador(String colaborador);

    List<OrdemServicoFinalizado> findByDataFinalizado(LocalDate dataFinalizado);

    List<OrdemServicoFinalizado> findByColaboradorAndDataFinalizado(String colaborador, LocalDate dataFinalizado);

    //SE OPTAR POR UMA BUSCA DE PROXIMIDADE, E NÃO APENAS BUSCA EXATA DO NOME, USE ESSE METÓDO:


    //@Query("SELECT os FROM OrdemServicoFinalizado os WHERE os.colaborador LIKE %:colaborador%")
    //List<OrdemServicoFinalizado> findByColaboradorContaining(@Param("colaborador") String colaborador);
}
