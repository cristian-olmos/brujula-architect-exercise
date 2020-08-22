package es.brujula.searcher.application.command.rooms.create;

import es.brujula.searcher.domain.hotel.model.Hotel;
import es.brujula.searcher.domain.hotel.repository.HotelRepository;
import es.brujula.searcher.domain.rooms.model.Room;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class CreateARoomCommandHandler implements CommandHandler<CreateARoomCommand> {

    private final HotelRepository hotels;
    private final RoomRepository rooms;

    public CreateARoomCommandHandler(HotelRepository hotels, RoomRepository rooms) {
        this.hotels = hotels;
        this.rooms = rooms;
    }

    public void handle(final CreateARoomCommand command) {

        //TODO this.ensureThatRoomDoesNotExists(command.getId());

        Hotel hotel = repository.byIdOrFail(command.getIdHotel());

        Room room = this.createRoom(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getOccupation()
        );

        hotel.addRoom(room);

        this.rooms.save(hotel);

    }

//    private void ensureThatRoomDoesNotExists(String id) {
//        Optional<Room> hotel = this.repository.byId(id);
//        if (hotel.isPresent()) {
//            throw new HotelAlreadyExistsException(id);
//        }
//    }

    private Room createRoom(String id, String name, Double price, String occupation) {
        return Room.create(id, name, price, occupation);
    }
}
