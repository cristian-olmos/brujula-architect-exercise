package es.brujula.searcher.application.command.hotels.modify;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class ModifyAHotelCommandHandler implements CommandHandler<ModifyAHotelCommand> {

    private final HotelRepository repository;

    public ModifyAHotelCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final ModifyAHotelCommand command) {

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
