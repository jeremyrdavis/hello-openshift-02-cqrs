package com.redhat.greetings.cqrs.infrastructure;

import com.redhat.greetings.cqrs.domain.Greeting;
import com.redhat.greetings.cqrs.domain.GreetingDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest @QuarkusTestResource(CQRSGreetingServiceTestResource.class)
public class CQRSGreetingServiceKafkaTest {

    @Inject
    @Any
    InMemoryConnector connector;

    private String text = "We are NOT violating the GPL!";

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testOnVerifiedGreeting() {

        InMemorySource<GreetingDTO> source = connector.source("greetings-verified");
        GreetingDTO greetingDTO = new GreetingDTO(null, text, "null", null, null, false);
        source.send(greetingDTO);

        List<Greeting> allGreetings = Greeting.listAll();
        assertEquals(1, allGreetings.size());
    }
}
