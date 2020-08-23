package es.brujula.searcher.application.command.service.create;

import es.brujula.searcher.domain.service.exception.ServiceAlreadyExistsException;
import es.brujula.searcher.domain.service.model.Services;
import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class CreateAServiceCommandHandler implements CommandHandler<CreateAServiceCommand> {

    private final ServiceRepository repository;

    public CreateAServiceCommandHandler(ServiceRepository repository) {
        this.repository = repository;
    }

    public void handle(final CreateAServiceCommand command) {

        this.ensureThatServiceDoesNotExists(command.id());

        Services anServices = this.createService(
                command.id(),
                command.name()
        );

        this.repository.add(anServices);

    }

    private void ensureThatServiceDoesNotExists(String id) {
        Optional<Services> service = this.repository.byId(id);
        if (service.isPresent()) {
            throw new ServiceAlreadyExistsException(id);
        }
    }

    private Services createService(String id, String name) {
        return Services.create(id, name);
    }
}
