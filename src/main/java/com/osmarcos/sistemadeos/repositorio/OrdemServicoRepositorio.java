package com.osmarcos.sistemadeos.repositorio;

import com.osmarcos.sistemadeos.entidades.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface OrdemServicoRepositorio extends JpaRepository<OrdemServico,Long>{
    
    @Query("SELECT COUNT(os) > 0 FROM OrdemServico os WHERE os.produto.marca.id = :marcaId")
    boolean existsByProdutoMarcaId(@Param("marcaId") Long marcaId);

    


}
