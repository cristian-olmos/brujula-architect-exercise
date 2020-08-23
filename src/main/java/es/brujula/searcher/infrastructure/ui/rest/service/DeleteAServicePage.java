package es.brujula.searcher.infrastructure.ui.rest.service;

import es.brujula.searcher.application.command.service.delete.DeleteAServiceCommand;
import es.brujula.searcher.application.command.service.delete.DeleteAServiceCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/services")
final class DeleteAServicePage {

    private final DeleteAServiceCommandHandler commandHandler;

    public DeleteAServicePage(DeleteAServiceCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void index(@PathVariable final String id) {
        DeleteAServiceCommand command = new DeleteAServiceCommand(id);
        this.commandHandler.handle(command);
    }
}
