    package com.osmarcos.sistemadeos.services;
    import java.util.Optional;
    import org.springframework.beans.factory.annotation.Autowired;
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

        @Transactional
        public ResponseEntity<Object> deletarMarca(String nome) {

            Optional<Marcas> marcaOpt = marcasRepository.findByNome(nome);
            if (marcaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Marca não encontrada.");
            }
    
            Long id = marcaOpt.get().getId();
    
            if (produtosRepository.existsByMarcaId(id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: Não é possível excluir a marca, pois existem produtos vinculados a ela.");
            }
    
            if (ordemServicoRepository.existsByProdutoMarcaId(id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: Não é possível excluir a marca, pois há ordens de serviço em aberto ou andamento para produtos dessa marca.");
            }
    
            marcasRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sucesso: A marca foi excluída com sucesso");
        }
    }

