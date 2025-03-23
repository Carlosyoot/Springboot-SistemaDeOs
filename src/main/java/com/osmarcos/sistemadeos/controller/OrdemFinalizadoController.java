package com.osmarcos.sistemadeos.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmarcos.sistemadeos.entidades.OrdemServicoFinalizado;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoFinalizadoRepositorio;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/ordens-finalizadas")
public class OrdemFinalizadoController {

    @Autowired
    private OrdemServicoFinalizadoRepositorio ordemServicoFinalizadoRepository;

    @GetMapping
    public ResponseEntity<List<OrdemServicoFinalizado>> filtrarOrdens(
            @RequestParam(required = false) String colaborador,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<OrdemServicoFinalizado> ordensFiltradas;

        if (colaborador != null && data != null) {
            // Filtra por colaborador e data
            ordensFiltradas = ordemServicoFinalizadoRepository.findByColaboradorAndDataFinalizado(colaborador, data);
        } else if (colaborador != null) {
            // Filtra apenas por colaborador
            ordensFiltradas = ordemServicoFinalizadoRepository.findByColaborador(colaborador);
        } else if (data != null) {
            // Filtra apenas por data
            ordensFiltradas = ordemServicoFinalizadoRepository.findByDataFinalizado(data);
        } else {
            // Retorna todas as ordens se nenhum filtro for fornecido
            ordensFiltradas = ordemServicoFinalizadoRepository.findAll();
        }

        return ResponseEntity.ok(ordensFiltradas);
    }
}