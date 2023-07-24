package com.redhat.greetings.cqrs.infrastructure;

import com.redhat.greetings.cqrs.domain.Greeting;
import com.redhat.greetings.cqrs.domain.GreetingDTO;
import com.redhat.greetings.cqrs.domain.SourceSystem;
import com.redhat.greetings.cqrs.domain.GreetingRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CQRSGreetingServiceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(CQRSGreetingServiceTest.class);

    @Inject
    CQRSGreetingService cqrsGreetingService;

    @InjectMock
    GreetingRepository greetingRepository;

    @BeforeEach
    void setUp() {
        Mockito.when(greetingRepository.streamAll()).thenReturn(Arrays.asList(
                new Greeting[]{
                        new Greeting("Hi, there!", "Lemmy", SourceSystem.REST_API, Instant.now(), true),
                        new Greeting("Hi, there!", "Lemmy", SourceSystem.REST_API, Instant.now(), true),
                }
        ).stream());
    }

    @Test
    public void testAllGreetings() {

        List<GreetingDTO> results = cqrsGreetingService.listAllGreetings();
        assertNotNull(results);
        assertEquals(2, results.size());
    }
}
