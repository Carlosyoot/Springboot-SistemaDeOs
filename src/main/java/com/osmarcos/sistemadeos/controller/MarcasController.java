package com.osmarcos.sistemadeos.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.osmarcos.sistemadeos.entidades.Marcas;
import com.osmarcos.sistemadeos.repositorio.MarcasRepositorio;
import com.osmarcos.sistemadeos.services.MarcasService;

@RestController
@RequestMapping("/marcas")
public class MarcasController {

    @Autowired
    private MarcasService marcasService;

    @Autowired
    private MarcasRepositorio marcasRepository;

    @GetMapping
    public List<String> listar() {
        return marcasRepository.findAllNomes();
    }

    @PostMapping
    public ResponseEntity<Object> criarMarca(@RequestBody Marcas marca, Locale locale) {
        return marcasService.criarMarca(marca, locale);
    }

    @DeleteMapping("/nome")
    public ResponseEntity<Object> deletarMarca(@RequestParam String nome, Locale locale) {
        return marcasService.deletarMarca(nome, locale);
    }
}