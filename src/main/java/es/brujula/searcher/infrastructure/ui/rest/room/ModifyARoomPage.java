package es.brujula.searcher.infrastructure.ui.rest.room;

import es.brujula.searcher.application.command.room.modify.ModifyARoomCommand;
import es.brujula.searcher.application.command.room.modify.ModifyARoomCommandHandler;
import es.brujula.searcher.infrastructure.ui.rest.room.dto.RoomRequest;
import es.brujula.shared.CommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rooms")
final class ModifyARoomPage {

    private final CommandHandler<ModifyARoomCommand> commandHandler;

    ModifyARoomPage(ModifyARoomCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void index(@PathVariable("id") final String id, @RequestBody final RoomRequest request) {

        ModifyARoomCommand command = new ModifyARoomCommand(
                id,
                request.getHotelId(),
                request.getName(),
                request.getPrice(),
                request.getOccupation()
        );

        this.commandHandler.handle(command);
    }
}
