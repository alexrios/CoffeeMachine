package com.alexrios.cm;

import org.cfg4j.provider.ConfigurationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RemoteConfiguration<T> {

    @Autowired
    private ConfigurationProvider cfgProvider;

    public T get(String key, T defaultValue, Class<T> clazz) {
        Optional<T> property;
        try {
            property = Optional.ofNullable(cfgProvider.getProperty(key, clazz));
        } catch (NoSuchElementException nse) {
            return defaultValue;
        }
        return property.orElse(defaultValue);
    }

}
