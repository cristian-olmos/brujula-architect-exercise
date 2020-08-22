package es.brujula.searcher.infrastructure.ui.rest.rooms;

import es.brujula.searcher.application.command.rooms.create.CreateARoomCommand;
import es.brujula.searcher.application.command.rooms.create.CreateARoomCommandHandler;
import es.brujula.searcher.infrastructure.UuidGenerator;
import es.brujula.searcher.infrastructure.ui.rest.rooms.dto.RoomRequest;
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
@RequestMapping("/v1/hotels/{hotelId}/rooms")
final class CreateARoomPage {

    private final CommandHandler commandHandler;
    private final UuidGenerator uuidGenerator;

    CreateARoomPage(CreateARoomCommandHandler commandHandler, UuidGenerator uuidGenerator) {
        this.commandHandler = commandHandler;
        this.uuidGenerator = uuidGenerator;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable String hotelId, @NotNull @Valid @RequestBody final RoomRequest room) {

        this.commandHandler.handle(
                new CreateARoomCommand(
                        uuidGenerator.next(),
                        hotelId,
                        room.getName(),
                        room.getPrice(),
                        room.getOccupation()
                )
        );
    }

}
