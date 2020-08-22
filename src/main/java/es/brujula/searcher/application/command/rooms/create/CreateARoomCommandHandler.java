package es.brujula.searcher.application.command.rooms.create;

import es.brujula.searcher.domain.hotel.exception.HotelAlreadyExistsException;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class CreateARoomCommandHandler implements CommandHandler<CreateARoomCommand> {

    private final HotelRepository repository;

    public CreateARoomCommandHandler(HotelRepository repository) {
        this.repository = repository;
    }

    public void handle(final CreateARoomCommand command) {

        this.ensureThanHotelDoesNotExists(command.getId());

        Room room = this.createRoom(
                command.getId(),
                command.getIdHotel(),
                command.getName(),
                command.getPrice(),
                command.getOccupation()
        );

        this.repository.add(room);

    }

    private void ensureThanHotelDoesNotExists(String id) {
        Optional<Room> hotel = this.repository.byId(id);
        if (hotel.isPresent()) {
            throw new HotelAlreadyExistsException(id);
        }
    }

    private Room createRoom(String id, String idHotel, String name, Double price, String occupation))

    {
        return Room.create(id, idHotel, name, price, occupation);
    }
}
