package es.brujula.searcher.infrastructure.ui.rest.hotel;

import es.brujula.searcher.application.command.hotels.addservice.AddServiceToHotelCommand;
import es.brujula.searcher.application.command.hotels.addservice.AddServiceToHotelCommandHandler;
import es.brujula.searcher.infrastructure.UuidGenerator;
import es.brujula.searcher.infrastructure.ui.rest.hotel.dto.AddServiceToHotelRequest;
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
@RequestMapping("/v1/hotels/{hotelId}/services}")
final class AddServiceToHotelPage {

    private final CommandHandler<AddServiceToHotelCommand> commandHandler;
    private final UuidGenerator uuidGenerator;

    AddServiceToHotelPage(AddServiceToHotelCommandHandler commandHandler, UuidGenerator uuidGenerator) {
        this.commandHandler = commandHandler;
        this.uuidGenerator = uuidGenerator;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable String hotelId, @NotNull @Valid @RequestBody final AddServiceToHotelRequest req) {

        this.commandHandler.handle(
                new AddServiceToHotelCommand(hotelId, req.getServiceId()));
    }
}
