package com.alexrios.cm;

import org.cfg4j.provider.ConfigurationProvider;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoffeeMachineApplication.class)
@ActiveProfiles("test")
public class CoffeeMachineApplicationTest {

    @Autowired
    private ConfigurationProvider provider;

    @org.junit.Test
    public void should_instantiate_a_consul_provider() throws Exception {
        assertThat(provider, is(notNullValue()));
    }
}