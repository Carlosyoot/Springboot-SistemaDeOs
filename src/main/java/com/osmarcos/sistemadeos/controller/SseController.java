package com.osmarcos.sistemadeos.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.osmarcos.sistemadeos.services.UpdateEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {

    private final UpdateEmitter sseService;

    public SseController(UpdateEmitter sseService) {
        this.sseService = sseService;
    }

    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return sseService.subscribe();
    }
}
    
