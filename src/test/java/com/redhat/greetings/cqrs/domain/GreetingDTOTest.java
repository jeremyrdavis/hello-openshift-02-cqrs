package com.redhat.greetings.cqrs.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class GreetingDTOTest {

    private final String text = "Good, morning!";
    private final String author = "Me";
    private final Instant createdAt = Instant.now();

    @Test
    public void testConstructors() {
        GreetingDTO greetingDTO = new GreetingDTO(text, author, SourceSystem.REST_API, createdAt, true);
        assertTrue(greetingDTO.isVerifiedFamilyFriendly());
        assertEquals(author, greetingDTO.author());
        assertEquals(text, greetingDTO.text());
        assertEquals(SourceSystem.REST_API, greetingDTO.sourceSystem());
        assertNull(greetingDTO.id());
    }
}
