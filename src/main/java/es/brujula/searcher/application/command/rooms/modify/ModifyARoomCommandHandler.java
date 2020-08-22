package es.brujula.searcher.application.command.rooms.modify;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class ModifyARoomCommandHandler implements CommandHandler<ModifyARoomCommand> {

    private final HotelRepository repository;

    public ModifyARoomCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final ModifyARoomCommand command) {

        String id = command.id();
        String name = command.name();
        String address = command.address();
        String category = command.category();

        Hotel anHotel = this.obtainHotel(id);
        anHotel.modify(name, address, category);

        this.repository.save(anHotel);
    }

    private Hotel obtainHotel(String id) {
        return this.repository.byIdOrFail(id);
    }
}
