package com.example.reactiveone.controllers;

import com.example.reactiveone.model.Temperature;
import com.example.reactiveone.model.TemperatureSensor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.example.reactiveone.model.RxSeeEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class TemperatureController {
   private final TemperatureSensor temperatureSensor;

   TemperatureController(TemperatureSensor temperatureSensor) {
       this.temperatureSensor = temperatureSensor;
   }

    @RequestMapping(
            value = "/temperature-stream",
            method = RequestMethod.GET)
    public SseEmitter events(HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter();

        temperatureSensor.temperatureStream()
                .subscribe(emitter.getSubscriber());

        return emitter;
    }
}
