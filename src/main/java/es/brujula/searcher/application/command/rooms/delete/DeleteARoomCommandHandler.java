package es.brujula.searcher.application.command.rooms.delete;

import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class DeleteARoomCommandHandler implements CommandHandler<DeleteARoomCommand> {

    private final HotelRepository repository;

    public DeleteARoomCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final DeleteARoomCommand command) {

        String id = command.id();

        this.deleteHotel(id);

    }

    private void deleteHotel(String id) {
        this.repository.byIdOrFail(id);
        this.repository.delete(id);
    }
}
