package es.brujula.searcher.application.command.hotels.delete;

import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class DeleteAHotelCommandHandler implements CommandHandler<DeleteAHotelCommand> {

    private final HotelRepository repository;

    public DeleteAHotelCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final DeleteAHotelCommand command) {

        String id = command.id();

        this.deleteHotel(id);

    }

    private void deleteHotel(String id) {
        this.repository.byIdOrFail(id);
        this.repository.delete(id);
    }
}
