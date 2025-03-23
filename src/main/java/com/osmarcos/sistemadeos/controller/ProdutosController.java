package com.osmarcos.sistemadeos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmarcos.sistemadeos.entidades.Produtos;
import com.osmarcos.sistemadeos.repositorio.ProdutoRepositorio;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
    
    @Autowired
    ProdutoRepositorio produtorepository;



    @GetMapping
    public List<Produtos> listar() {
        return produtorepository.findAll();
    }

}
