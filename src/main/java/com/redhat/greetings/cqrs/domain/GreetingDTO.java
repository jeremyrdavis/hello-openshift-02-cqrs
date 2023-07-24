package com.redhat.greetings.cqrs.domain;

import java.time.Instant;

public record GreetingDTO(Long id, String text, String author, SourceSystem sourceSystem, Instant createdAt, boolean isVerifiedFamilyFriendly) {

    public GreetingDTO(String text, String author, SourceSystem sourceSystem, Instant createdAt, boolean isVerifiedFamilyFriendly) {
        this(null, text, author, sourceSystem, createdAt, isVerifiedFamilyFriendly);
    }
}
