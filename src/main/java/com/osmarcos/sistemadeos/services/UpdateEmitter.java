package com.osmarcos.sistemadeos.services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class UpdateEmitter {

    @Autowired
    private MessageSource messageSource;

    
    private final List<SseEmitter> emitters = new ArrayList<>();

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); 
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitters.add(emitter);
        return emitter;
    }

    public void notificarTodos() {

        String Msg = messageSource.getMessage("update_notification", null, Locale.getDefault());


        List<SseEmitter> emittersMortos = new ArrayList<>();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().data(Msg));
            } catch (IOException e) {
                emittersMortos.add(emitter);
            }
        });
        emitters.removeAll(emittersMortos);
    }

    

}
