package com.osmarcos.sistemadeos.services;

import com.osmarcos.sistemadeos.entidades.OrdemServico;
import com.osmarcos.sistemadeos.entidades.OrdemServicoFinalizado;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoFinalizadoRepositorio;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class OrdemService {

    @Autowired
    private OrdemServicoRepositorio ordemServicoRepository;

    @Autowired
    private OrdemServicoFinalizadoRepositorio ordemServicoFinalizadoRepository;

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<Object> criarOrdemServico(OrdemServico ordemServico) {
        try {

            ordemServico.setDataAbertura(LocalDate.now());
            OrdemServico novaOs = ordemServicoRepository.save(ordemServico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaOs);

        } catch (Exception exception) {

            String Msg = messageSource.getMessage("erro_criar_os", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Msg);
        }
    }

    public ResponseEntity<Object> atualizarStatus(Long id, Map<String, String> statusUpdate) {
        try {

            Optional<OrdemServico> ordemServicoOptional = ordemServicoRepository.findById(id);
            if (!ordemServicoOptional.isPresent()) {

                String Msg = messageSource.getMessage("ordem_servico_nao_encontrada", null, Locale.getDefault());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Msg);
            }

            OrdemServico ordemServico = ordemServicoOptional.get();
            String novoStatus = statusUpdate.get("status");

            if (novoStatus == null || novoStatus.isEmpty() || !novoStatus.equals("Andamento") && !novoStatus.equals("Concluído")) {

                String Msg = messageSource.getMessage("invalid_status", null, Locale.getDefault());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Msg);
            }

            if (novoStatus.equals("Concluído")) {
                moverParaFinalizado(ordemServico);
                String Msg = messageSource.getMessage("ordem_finalizada", null, Locale.getDefault());
                return ResponseEntity.status(HttpStatus.OK).body(Msg);

            } else {

                ordemServico.setStatus(novoStatus);
                ordemServicoRepository.save(ordemServico);
                return ResponseEntity.status(HttpStatus.OK).body(ordemServico);

            }
        } catch (Exception e) {

            String Msg = messageSource.getMessage("erro_status", null, Locale.getDefault());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(  Msg + e.getMessage());
        }
    }

    public void moverParaFinalizado(OrdemServico ordemServico) {
        OrdemServicoFinalizado ordemFinalizada = new OrdemServicoFinalizado();
        ordemFinalizada.setProduto(ordemServico.getDescricao());
        ordemFinalizada.setDataAbertura(ordemServico.getDataAbertura());
        ordemFinalizada.setDataFinalizado(LocalDate.now());
        ordemFinalizada.setColaborador(ordemServico.getColaborador().getNome());
        ordemFinalizada.setStatus("Concluído");

        ordemServicoFinalizadoRepository.save(ordemFinalizada);
        ordemServicoRepository.delete(ordemServico);
    }

    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoRepository.findAll();
    }
}