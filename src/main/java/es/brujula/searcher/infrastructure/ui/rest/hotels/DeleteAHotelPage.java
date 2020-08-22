package es.brujula.searcher.infrastructure.ui.rest.hotels;

import es.brujula.searcher.application.command.hotels.delete.DeleteAHotelCommand;
import es.brujula.searcher.application.command.hotels.delete.DeleteAHotelCommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hotels")
final class DeleteAHotelPage {

    private final DeleteAHotelCommandHandler commandHandler;

    public DeleteAHotelPage(DeleteAHotelCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void index(@PathVariable final String id) {
        DeleteAHotelCommand command = new DeleteAHotelCommand(id);
        this.commandHandler.handle(command);
    }
}
