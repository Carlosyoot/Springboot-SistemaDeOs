    package com.osmarcos.sistemadeos.controller;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import com.osmarcos.sistemadeos.repositorio.ColaboradorRepositorio;
    import com.osmarcos.sistemadeos.services.ColaboradoresService;

    import jakarta.validation.Valid;

    import com.osmarcos.sistemadeos.entidades.Colaborador;





    @RestController
    @RequestMapping("/colaboradores")
    public class ColaboradorController {

        @Autowired
        private ColaboradorRepositorio colaboradorRepository;

        @Autowired
        private ColaboradoresService service;

        @GetMapping()
        public List<String> listar(){
            return colaboradorRepository.findAllNomes();
        }

        @GetMapping("/nome")
        public ResponseEntity<Object> obterPorNome(@RequestParam String nome) {
            return service.obterDetalhesPorNome(nome);
        }
        
        @PostMapping
        public ResponseEntity<Object> criarColaborador(@Valid @RequestBody Colaborador colaborador) {
            return service.criarColaborador(colaborador); 
        }

        @DeleteMapping("/nome")
        public ResponseEntity<Object> deletarColaborador(@RequestParam String nome) {
            return service.deletarColaborador(nome);
        }
    }

