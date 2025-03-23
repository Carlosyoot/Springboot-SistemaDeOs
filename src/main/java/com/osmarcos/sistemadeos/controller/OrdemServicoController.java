package com.osmarcos.sistemadeos.controller;

import com.osmarcos.sistemadeos.entidades.OrdemServico;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoRepositorio;
import com.osmarcos.sistemadeos.services.OrdemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoRepositorio ordemServicoRepository;

    @Autowired
    private OrdemService service;


    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    
    @PostMapping
    public ResponseEntity<Object> criarOrdemServico(@RequestBody OrdemServico ordemServico) {
        try {
            ordemServico.setDataAbertura(LocalDate.now());  
            OrdemServico novaOs = ordemServicoRepository.save(ordemServico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaOs);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar a ordem de serviço.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        try {

            Optional<OrdemServico> ordemServicoOptional = ordemServicoRepository.findById(id);
            if (!ordemServicoOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de serviço não encontrada.");
            }

            OrdemServico ordemServico = ordemServicoOptional.get();
            
            String novoStatus = statusUpdate.get("status");
            if (novoStatus == null || novoStatus.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status inválido.");
            }

            if (!novoStatus.equals("Andamento") && !novoStatus.equals("Concluído")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status deve ser 'Andamento' ou 'Concluído'.");
            }

            if (novoStatus.equals("Concluído")) {
                service.moverParaFinalizado(ordemServico);
                return ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço movida para finalizado.");
            } else {
                ordemServico.setStatus(novoStatus);
                ordemServicoRepository.save(ordemServico);
                return ResponseEntity.status(HttpStatus.OK).body(ordemServico);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o status da ordem de serviço: " + e.getMessage());
        }
    }
}
