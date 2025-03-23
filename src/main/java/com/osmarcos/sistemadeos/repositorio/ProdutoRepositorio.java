package com.osmarcos.sistemadeos.repositorio;

import com.osmarcos.sistemadeos.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProdutoRepositorio extends JpaRepository<Produtos,Long>{
    
    boolean existsByNome(String nome);
    boolean existsByMarcaId(Long marcaId);


}

