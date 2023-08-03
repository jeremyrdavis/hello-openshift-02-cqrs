package com.redhat.greetings.cqrs.infrastructure;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class CQRSGreetingServiceTestResource implements QuarkusTestResourceLifecycleManager {
    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> props1 = InMemoryConnector.switchIncomingChannelsToInMemory("greetings-verified");

        env.putAll(props1);
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
