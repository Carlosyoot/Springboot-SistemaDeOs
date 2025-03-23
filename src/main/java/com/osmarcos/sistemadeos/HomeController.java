package com.osmarcos.sistemadeos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";  // Retorna o template 'index.html' localizado na pasta 'templates'
    }
}
