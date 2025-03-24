package com.osmarcos.sistemadeos.services;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.osmarcos.sistemadeos.entidades.Marcas;
import com.osmarcos.sistemadeos.repositorio.MarcasRepositorio;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoRepositorio;
import com.osmarcos.sistemadeos.repositorio.ProdutoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class MarcasService {

    @Autowired
    private MarcasRepositorio marcasRepository;

    @Autowired
    private ProdutoRepositorio produtosRepository;

    @Autowired
    private OrdemServicoRepositorio ordemServicoRepository;

    @Autowired
    private UpdateEmitter update;

    @Autowired
    private MessageSource messageSource;

    @Transactional
    public ResponseEntity<Object> criarMarca(Marcas marca, Locale locale) {
        try {
            if (marcasRepository.existsByNome(marca.getNome())) {
                String msg = messageSource.getMessage("marca.nome_em_uso", null, locale);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }

            Marcas novaMarca = marcasRepository.save(marca);

            update.notificarTodos();

            return ResponseEntity.status(HttpStatus.CREATED).body(novaMarca);
        } catch (DataIntegrityViolationException e) {
            String msg = messageSource.getMessage("marca.erro_integridade", null, locale);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    @Transactional
    public ResponseEntity<Object> deletarMarca(String nome, Locale locale) {
        Optional<Marcas> marcaOpt = marcasRepository.findByNome(nome);
        if (marcaOpt.isEmpty()) {
            String msg = messageSource.getMessage("marca.nao_encontrada", null, locale);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }

        Long id = marcaOpt.get().getId();

        if (produtosRepository.existsByMarcaId(id)) {
            String msg = messageSource.getMessage("marca.produtos_vinculados", null, locale);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        if (ordemServicoRepository.existsByProdutoMarcaId(id)) {
            String msg = messageSource.getMessage("marca.ordens_em_aberto", null, locale);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        marcasRepository.deleteById(id);
        String msg = messageSource.getMessage("marca.excluida_sucesso", null, locale);

        update.notificarTodos();


        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}