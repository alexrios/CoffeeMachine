package com.alexrios.cm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import static java.util.Calendar.SECOND;
import static java.util.concurrent.Executors.newScheduledThreadPool;

@Component
@EnableScheduling
public class CoffeeTaskScheduler implements SchedulingConfigurer {

    @Autowired
    private RemoteConfiguration<Integer> configurations;

    @Autowired
    private CoffeeMachine coffeeMachine;

    @Value("${default.scheduling.time:5}")
    private int defaultSchedulingTime;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(newScheduledThreadPool(1));
        taskRegistrar.addTriggerTask(() -> coffeeMachine.prepare(), tc -> configureNextTrigger(tc));
    }

    private Date configureNextTrigger(TriggerContext triggerContext) {
        Optional<Date> optLastExecution = Optional.ofNullable(triggerContext.lastActualExecutionTime());

        Calendar nextExecutionTime = new GregorianCalendar();
        nextExecutionTime.setTime(optLastExecution.orElse(new Date()));
        nextExecutionTime.add(SECOND, configurations.get("preparing-rate", defaultSchedulingTime, Integer.class));

        return nextExecutionTime.getTime();
    }

}
