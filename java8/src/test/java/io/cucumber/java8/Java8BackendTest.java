package io.cucumber.java8;

import io.cucumber.core.backend.Glue;
import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.java8.individualclasssteps.StepsTwo;
import io.cucumber.java8.steps.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.util.Arrays;

import static java.lang.Thread.currentThread;
import static org.mockito.Mockito.verify;

@ExtendWith({ MockitoExtension.class })
class Java8BackendTest {

    @Mock
    private Glue glue;

    @Mock
    private ObjectFactory factory;

    private Java8Backend backend;

    @BeforeEach
    void createBackend() {
        this.backend = new Java8Backend(factory, factory, currentThread()::getContextClassLoader);
    }

    @Test
    void finds_step_definitions_by_classpath_url() {
        backend.loadGlue(glue, Arrays.asList(URI.create("classpath:io/cucumber/java8/steps"),
            URI.create("classpath:io/cucumber/java8/individualclasssteps/StepsTwo")));
        backend.buildWorld();
        verify(factory).addClass(Steps.class);
        verify(factory).addClass(StepsTwo.class);
    }

}
