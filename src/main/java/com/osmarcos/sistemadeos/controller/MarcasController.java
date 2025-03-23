package com.osmarcos.sistemadeos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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

import jakarta.transaction.Transactional;



@RestController
@RequestMapping("/marcas")
public class MarcasController {

    @Autowired
    MarcasRepositorio marcasRepository;

    @Autowired
    MarcasService service;

    @GetMapping
    public List<String> listar() {
        return marcasRepository.findAllNomes();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> criarNovaMarca(@RequestBody Marcas marca) {
        try {

            if (marcasRepository.existsByNome(marca.getNome())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: O nome da marca já está em uso.");
            }

            Marcas novaMarca = marcasRepository.save(marca); 
            return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
        } catch (DataIntegrityViolationException e) {
    

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de integridade: Algo ocorreu no\n banco de dados");
        }
    }

    @DeleteMapping("/nome")
    public ResponseEntity<Object> deletarMarca(@RequestParam String nome) {
        return service.deletarMarca(nome);
    }
    
}
