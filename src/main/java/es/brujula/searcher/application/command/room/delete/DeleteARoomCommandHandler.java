package es.brujula.searcher.application.command.room.delete;

import es.brujula.searcher.domain.room.repository.RoomRepository;
import es.brujula.shared.CommandHandler;
import org.springframework.stereotype.Service;

@Service
public final class DeleteARoomCommandHandler implements CommandHandler<DeleteARoomCommand> {

    private final RoomRepository repository;

    public DeleteARoomCommandHandler(RoomRepository repository) {
        this.repository = repository;
    }

    public void handle(final DeleteARoomCommand command) {

        String id = command.id();

        this.deleteRoom(id);

    }

    private void deleteRoom(String id) {
        this.repository.byIdOrFail(id);
        this.repository.delete(id);
    }
}
