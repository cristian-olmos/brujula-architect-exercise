package es.brujula.searcher.application.command.hotel.create;

import es.brujula.searcher.domain.hotel.exception.HotelAlreadyExistsException;
import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class CreateAHotelCommandHandler implements CommandHandler<CreateAHotelCommand> {

    private final HotelRepository repository;

    public CreateAHotelCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final CreateAHotelCommand command) {

        this.ensureThanHotelDoesNotExists(command.id());

        Hotel anHotel = this.createHotel(
                command.id(),
                command.name(),
                command.address(),
                command.category()
        );

        this.repository.add(anHotel);

    }

    private void ensureThanHotelDoesNotExists(String id) {
        Optional<Hotel> hotel = this.repository.byId(id);
        if (hotel.isPresent()) {
            throw new HotelAlreadyExistsException(id);
        }
    }

    private Hotel createHotel(String id, String name, String address, String category) {
        return Hotel.create(id, name, address, category);
    }
}
