package es.brujula.searcher.infrastructure.ui.rest.room;

import es.brujula.searcher.application.command.room.create.CreateARoomCommand;
import es.brujula.searcher.application.command.room.create.CreateARoomCommandHandler;
import es.brujula.searcher.infrastructure.UuidGenerator;
import es.brujula.searcher.infrastructure.ui.rest.room.dto.RoomRequest;
import es.brujula.shared.CommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/rooms/{roomId}/rooms")
final class CreateARoomPage {

    private final CommandHandler<CreateARoomCommand> commandHandler;
    private final UuidGenerator uuidGenerator;

    CreateARoomPage(CreateARoomCommandHandler commandHandler, UuidGenerator uuidGenerator) {
        this.commandHandler = commandHandler;
        this.uuidGenerator = uuidGenerator;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable String roomId, @NotNull @Valid @RequestBody final RoomRequest room) {

        this.commandHandler.handle(
                new CreateARoomCommand(
                        uuidGenerator.next(),
                        roomId,
                        room.name(),
                        room.price(),
                        room.occupation()
                )
        );
    }

}
