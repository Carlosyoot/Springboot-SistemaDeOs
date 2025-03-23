package com.osmarcos.sistemadeos.services;

import com.osmarcos.sistemadeos.entidades.OrdemServico;
import com.osmarcos.sistemadeos.entidades.OrdemServicoFinalizado;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoFinalizadoRepositorio;
import com.osmarcos.sistemadeos.repositorio.OrdemServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrdemService {
    
    
    @Autowired
    private OrdemServicoRepositorio ordemServicoRepository;

    @Autowired
    private OrdemServicoFinalizadoRepositorio ordemServicoFinalizadoRepository;

    public void moverParaFinalizado(OrdemServico ordemServico) {
        OrdemServicoFinalizado ordemFinalizada = new OrdemServicoFinalizado();
        
        // Preenchendo os dados necessários
        ordemFinalizada.setProduto(ordemServico.getDescricao()); // "produto" é a descrição da ordem
        ordemFinalizada.setDataAbertura(ordemServico.getDataAbertura());
        ordemFinalizada.setDataFinalizado(LocalDate.now()); // Data de finalização é a data atual
        ordemFinalizada.setColaborador(ordemServico.getColaborador().getNome()); // Supondo que o nome do colaborador é necessário
        ordemFinalizada.setStatus("Concluído");

        // Salvar a ordem finalizada
        ordemServicoFinalizadoRepository.save(ordemFinalizada);

        // Deletar a ordem de serviço da tabela principal
        ordemServicoRepository.delete(ordemServico);
    }

}
