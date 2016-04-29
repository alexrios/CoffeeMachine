package com.alexrios.cm;

import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

public class CoffeeTaskSchedulerTest {

    private CoffeeTaskScheduler scheduler;
    private ScheduledTaskRegistrar registrar;

    @Before
    public void setUp() throws Exception {
        scheduler = new CoffeeTaskScheduler();
        registrar = new ScheduledTaskRegistrar();
    }

    @Test
    public void should_not_fail_with_simple_registrar() throws Exception {
        scheduler.configureTasks(registrar);
    }
}