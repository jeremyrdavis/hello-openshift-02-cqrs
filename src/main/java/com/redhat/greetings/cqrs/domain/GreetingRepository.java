package com.redhat.greetings.cqrs.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingRepository implements PanacheRepository<Greeting> {
}
