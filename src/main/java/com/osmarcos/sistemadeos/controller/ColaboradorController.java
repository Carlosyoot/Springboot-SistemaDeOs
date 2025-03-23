package com.osmarcos.sistemadeos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.osmarcos.sistemadeos.repositorio.ColaboradorRepositorio;
import com.osmarcos.sistemadeos.entidades.Colaborador;





@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorRepositorio colaboradorRepository;

    @GetMapping()
    public List<String> listar(){
        return colaboradorRepository.findAllNomes();
    }

    //@GetMapping("/{id}")
    //public ResponseEntity<Colaborador> obterPorId(@PathVariable Long id) {
    //    return colaboradorRepository.findById(id)
    //        .map(colaborador -> ResponseEntity.ok(colaborador))
    //        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    //}
    
    
    @PostMapping
    public ResponseEntity<Object> criarColaborador(@RequestBody Colaborador colaborador) {
    try {
        
        if (colaboradorRepository.existsByNome(colaborador.getNome())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro: O nome do colaborador já está em uso.");
        }

        Colaborador novoColaborador = colaboradorRepository.save(colaborador);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);

    } catch (DataIntegrityViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Erro de integridade: Algo ocorreu no banco de dados.");
        }
    }
}

