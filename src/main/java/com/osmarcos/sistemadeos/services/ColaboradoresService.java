package com.osmarcos.sistemadeos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.osmarcos.sistemadeos.entidades.Colaborador;
import com.osmarcos.sistemadeos.repositorio.ColaboradorRepositorio;
import jakarta.transaction.Transactional;
import java.util.Locale;

@Service
public class ColaboradoresService {
    
    @Autowired
    private ColaboradorRepositorio colaboradorRepository;

    @Autowired
    private UpdateEmitter update;

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PasswordEncoder encoder;



    public List<String> listarColaboradores() {
        return colaboradorRepository.findAllNomes();
    }

    @Transactional
    public ResponseEntity<Object> criarColaborador(Colaborador colaborador) {
        try {
            if (colaboradorRepository.existsByNome(colaborador.getNome())) {
                String msg = messageSource.getMessage("colaborador_nome_em_uso", null, Locale.getDefault());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
            }

            String senhaHash = encoder.encode(colaborador.getSenha());
            colaborador.setSenha(senhaHash);
            
            Colaborador novoColaborador = colaboradorRepository.save(colaborador);
            update.notificarTodos();

            return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);
        } catch (Exception e) {
            String msg = messageSource.getMessage("erro_integridade_banco", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }
    }

    public ResponseEntity<Object> obterDetalhesPorNome(String nome) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findByNome(nome);
        if (colaboradorOpt.isEmpty()) {

            String msg = messageSource.getMessage("colaborador_nao_encontrado", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
        return ResponseEntity.ok(colaboradorOpt.get());
    }

    @Transactional
    public ResponseEntity<Object> deletarColaborador(String nome) {
        Optional<Colaborador> colaboradorOpt = colaboradorRepository.findByNome(nome);
        if (colaboradorOpt.isEmpty()) {

            String msg = messageSource.getMessage("colaborador_nao_encontrado", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }

        Long id = colaboradorOpt.get().getId();
        colaboradorRepository.deleteById(id);

        update.notificarTodos(); 

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
