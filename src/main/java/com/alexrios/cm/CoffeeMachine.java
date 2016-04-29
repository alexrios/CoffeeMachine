package com.alexrios.cm;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log
@Component
public class CoffeeMachine {

    @Setter
    @Autowired
    private RemoteConfiguration<String> configurations;

    @Setter
    @Value("${machine-version}")
    private String machineVersion;

    @Setter
    @Value("${default.coffee.type:mocha}")
    private String defaultCoffeeType;

    public void prepare() {
        log.info(configurations.get("coffee-type", defaultCoffeeType, String.class));
    }

}
