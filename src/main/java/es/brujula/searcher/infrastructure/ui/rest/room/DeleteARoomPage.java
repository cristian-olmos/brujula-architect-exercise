package es.brujula.searcher.infrastructure.ui.rest.room;

import es.brujula.searcher.application.command.room.delete.DeleteARoomCommand;
import es.brujula.searcher.application.command.room.delete.DeleteARoomCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rooms")
final class DeleteARoomPage {

    private final DeleteARoomCommandHandler commandHandler;

    public DeleteARoomPage(DeleteARoomCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void index(@PathVariable final String id) {
        DeleteARoomCommand command = new DeleteARoomCommand(id);
        this.commandHandler.handle(command);
    }
}
