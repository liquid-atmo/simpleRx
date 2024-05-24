package com.example.reactiveone.model;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import rx.Observable;

import static java.util.concurrent.TimeUnit.*;

import java.util.Random;

@Component
public class TemperatureSensor {
    private final Random rnd = new Random();



    private final Observable<Temperature> dataStream =
            Observable
                    .range(0, Integer.MAX_VALUE)
                    .concatMap(tick -> Observable
                        .just(tick)
                        .delay(rnd.nextInt(5000), MILLISECONDS)
                        .map(tickValue -> this.probe())
                    )
                    .publish()
                    .refCount();

    private Temperature probe() {
        return new Temperature(16 + rnd.nextGaussian() * 10);
    }

    public Observable<Temperature> temperatureStream() {
        return dataStream;
    }


}
