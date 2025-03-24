package com.osmarcos.sistemadeos.services;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.osmarcos.sistemadeos.entidades.Produtos;
import com.osmarcos.sistemadeos.repositorio.ProdutoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ProdutosService {

    @Autowired
    private ProdutoRepositorio produtorepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UpdateEmitter update;

    @Transactional
    public ResponseEntity<Object> criarProduto(Produtos produto) {
        try {
            if (produtorepository.existsByNome(produto.getNome())) {

                String Msg = messageSource.getMessage("nome_produto", null, Locale.getDefault());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Msg);
            }

            Produtos novoProduto = produtorepository.save(produto);

            update.notificarTodos();

            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (DataIntegrityViolationException e) {

            String Msg = messageSource.getMessage("data_integrity_validation", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Msg);
        }
    }

    public List<Produtos> listarProdutos() {
        return produtorepository.findAll();
    }
}