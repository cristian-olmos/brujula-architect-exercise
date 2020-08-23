package es.brujula.searcher.application.command.room.create;

import es.brujula.searcher.domain.room.exception.RoomAlreadyExistsException;
import es.brujula.searcher.domain.room.model.Room;
import es.brujula.searcher.domain.room.repository.RoomRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class CreateARoomCommandHandler implements CommandHandler<CreateARoomCommand> {

    private final RoomRepository rooms;

    public CreateARoomCommandHandler(RoomRepository rooms) {
        this.rooms = rooms;
    }

    public void handle(final CreateARoomCommand command) {

        this.ensureThatRoomDoesNotExists(command.getId());

        Room room = this.createRoom(
                command.getId(),
                command.getHotelId(),
                command.getName(),
                command.getPrice(),
                command.getOccupation()
        );

        this.rooms.add(room);

    }

    private void ensureThatRoomDoesNotExists(String id) {
        Optional<Room> room = this.rooms.byId(id);
        if (room.isPresent()) {
            throw new RoomAlreadyExistsException(id);
        }
    }

    private Room createRoom(String id, String hotelId, String name, Double price, String occupation) {
        return Room.create(id, hotelId, name, price, occupation);
    }
}
