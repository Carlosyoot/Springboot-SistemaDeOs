package com.osmarcos.sistemadeos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.osmarcos.sistemadeos.entidades.Colaborador;
import com.osmarcos.sistemadeos.repositorio.ColaboradorRepositorio;
import jakarta.transaction.Transactional;


@Service
public class ColaboradoresService {
    
    @Autowired
    private ColaboradorRepositorio colaboradorRepository;

    public List<String> listarColaboradores() {
        return colaboradorRepository.findAllNomes();
    }

    @Transactional
    public ResponseEntity<Object> criarColaborador(Colaborador colaborador) {
        try {
            if (colaboradorRepository.existsByNome(colaborador.getNome())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: O nome do colaborador já está em uso.");
            }

            Colaborador novoColaborador = colaboradorRepository.save(colaborador);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de integridade: Algo ocorreu no banco de dados.");
        }
    }

    public ResponseEntity<Object> obterDetalhesPorNome(String nome) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findByNome(nome);
        if (colaboradorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Colaborador não encontrado.");
        }
        return ResponseEntity.ok(colaboradorOpt.get());
    }


    @Transactional
    public ResponseEntity<Object> deletarColaborador(String nome) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findByNome(nome);
        if (colaboradorOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Colaborador não encontrado.");
        }

        Long id = colaboradorOpt.get().getId();
        colaboradorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

