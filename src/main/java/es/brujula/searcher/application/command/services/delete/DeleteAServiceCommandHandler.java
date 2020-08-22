package es.brujula.searcher.application.command.services.delete;

import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class DeleteAServiceCommandHandler implements CommandHandler<DeleteAServiceCommand> {

    private final HotelRepository repository;

    public DeleteAServiceCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final DeleteAServiceCommand command) {

        String id = command.id();

        this.deleteHotel(id);

    }

    private void deleteHotel(String id) {
        this.repository.byIdOrFail(id);
        this.repository.delete(id);
    }
}
