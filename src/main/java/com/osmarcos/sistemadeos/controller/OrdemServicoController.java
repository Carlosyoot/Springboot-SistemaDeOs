package com.osmarcos.sistemadeos.controller;
import com.osmarcos.sistemadeos.entidades.OrdemServico;
import com.osmarcos.sistemadeos.services.OrdemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemService ordemService;

    @GetMapping
    public List<OrdemServico> listar() {
        return ordemService.listarOrdensServico();
    }

    @PostMapping
    public ResponseEntity<Object> criarOrdemServico(@RequestBody OrdemServico ordemServico) {
        return ordemService.criarOrdemServico(ordemServico);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        return ordemService.atualizarStatus(id, statusUpdate);
    }
}