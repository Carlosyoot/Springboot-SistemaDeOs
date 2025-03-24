package com.osmarcos.sistemadeos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmarcos.sistemadeos.entidades.Produtos;
import com.osmarcos.sistemadeos.services.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @GetMapping
    public List<Produtos> listar() {
        return produtosService.listarProdutos();
    }

    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody Produtos produto) {
        return produtosService.criarProduto(produto);
    }
}