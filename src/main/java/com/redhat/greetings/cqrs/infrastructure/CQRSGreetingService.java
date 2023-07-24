package com.redhat.greetings.cqrs.infrastructure;

import com.redhat.greetings.cqrs.domain.GreetingDTO;
import com.redhat.greetings.cqrs.domain.GreetingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CQRSGreetingService {

    static final Logger LOGGER = LoggerFactory.getLogger(CQRSGreetingService.class);

    @Inject
    GreetingRepository greetingRepository;

    public List<GreetingDTO> listAllGreetings() {

        return greetingRepository.streamAll().map(greeting -> {
            LOGGER.debug("greeting: {}", greeting);
            return new GreetingDTO(greeting.getId(), greeting.getText(), greeting.getAuthor(), greeting.getSourceSystem(), greeting.getCreatedAt(), greeting.isVerifiedFamilyFriendly());
        }).collect(Collectors.toList());
    }
}
