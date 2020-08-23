package es.brujula.searcher.application.command.room.modify;

import es.brujula.searcher.domain.room.model.Room;
import es.brujula.searcher.domain.room.repository.RoomRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class ModifyARoomCommandHandler implements CommandHandler<ModifyARoomCommand> {

    private final RoomRepository repository;

    public ModifyARoomCommandHandler(RoomRepository repository) {
        this.repository = repository;
    }

    public void handle(final ModifyARoomCommand command) {

        String id = command.id();
        String hotelId = command.hotelId();
        String name = command.name();
        Double price = command.price();
        String occupation = command.occupation();

        Room anRoom = this.obtainRoom(id);
        anRoom.modify(hotelId, name, price, occupation);

        this.repository.save(anRoom);
    }

    private Room obtainRoom(String id) {
        return this.repository.byIdOrFail(id);
    }
}
