package com.alexrios.cm;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;
    private RemoteConfiguration<String> provider;

    private static Logger log = Logger.getLogger(CoffeeMachine.class.getName());
    private static OutputStream logCapturingStream;
    private static StreamHandler customLogHandler;

    @Before
    public void setUp() throws Exception {
        logCapturingStream = new ByteArrayOutputStream();
        Handler[] handlers = log.getParent().getHandlers();
        customLogHandler = new StreamHandler(logCapturingStream, new SimpleFormatter());
        log.addHandler(customLogHandler);

        coffeeMachine = new CoffeeMachine();
        provider = mock(RemoteConfiguration.class);
        coffeeMachine.setConfigurations(provider);
        coffeeMachine.setDefaultCoffeeType("mocha");
        coffeeMachine.setMachineVersion("1");
    }

    @Test
    public void should_say_something_in_logs() throws Exception {
        when(provider.get("coffee-type", "mocha", String.class)).thenReturn("Ola");

        coffeeMachine.prepare();

        assertThat(capturedLog(), containsString("Ola"));
    }

    private String capturedLog() throws IOException {
        customLogHandler.flush();
        return logCapturingStream.toString();
    }
}