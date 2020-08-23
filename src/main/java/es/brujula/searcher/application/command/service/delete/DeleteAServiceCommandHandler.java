package es.brujula.searcher.application.command.service.delete;

import es.brujula.searcher.domain.service.repository.ServiceRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class DeleteAServiceCommandHandler implements CommandHandler<DeleteAServiceCommand> {

    private final ServiceRepository repository;

    public DeleteAServiceCommandHandler(ServiceRepository repository) {
        this.repository = repository;
    }

    public void handle(final DeleteAServiceCommand command) {

        String id = command.id();

        this.deleteService(id);

    }

    private void deleteService(String id) {
        this.repository.byIdOrFail(id);
        this.repository.delete(id);
    }
}
