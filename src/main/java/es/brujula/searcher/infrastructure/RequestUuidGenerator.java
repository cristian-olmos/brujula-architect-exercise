package es.brujula.searcher.infrastructure;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestUuidGenerator implements UuidGenerator {
    public String next() {
        return UUID.randomUUID().toString();
    }
}
