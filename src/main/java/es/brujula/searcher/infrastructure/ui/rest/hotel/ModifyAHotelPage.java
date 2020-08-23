package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.command.hotels.modify.ModifyAHotelCommand;
import es.brujula.searcher.application.command.hotels.modify.ModifyAHotelCommandHandler;
import es.brujula.searcher.infrastructure.ui.rest.hotel.dto.HotelRequest;
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
@RequestMapping("/v1/hotels")
final class ModifyAHotelPage {

    private final CommandHandler<ModifyAHotelCommand> commandHandler;

    ModifyAHotelPage(ModifyAHotelCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void index(@PathVariable("id") final String id, @RequestBody final HotelRequest request) {

        ModifyAHotelCommand command = new ModifyAHotelCommand(
                id,
                request.name(),
                request.address(),
                request.category()
        );

        this.commandHandler.handle(command);
    }
}
