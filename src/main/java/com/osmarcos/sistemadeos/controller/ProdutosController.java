package com.osmarcos.sistemadeos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmarcos.sistemadeos.entidades.Produtos;
import com.osmarcos.sistemadeos.repositorio.ProdutoRepositorio;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    
    @Autowired
    ProdutoRepositorio produtorepository;



    @GetMapping
    public List<Produtos> listar() {
        return produtorepository.findAll();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> criarProduto(@RequestBody Produtos produto){

        try{
        if(produtorepository.existsByNome(produto.getNome())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("O nome de produto já está em uso");
        }

        Produtos novoProduto =  produtorepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de integridade: Algo ocorreu no banco de dados.");
        }
        
    }

}
