package es.brujula.searcher.infrastructure.ui.rest.service;

import es.brujula.searcher.application.command.service.create.CreateAServiceCommand;
import es.brujula.searcher.application.command.service.create.CreateAServiceCommandHandler;
import es.brujula.searcher.infrastructure.UuidGenerator;
import es.brujula.searcher.infrastructure.ui.rest.service.dto.ServiceRequest;
import es.brujula.shared.CommandHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1/services/")
final class CreateAServicePage {

    private final CommandHandler<CreateAServiceCommand> commandHandler;
    private final UuidGenerator uuidGenerator;

    CreateAServicePage(CreateAServiceCommandHandler commandHandler, UuidGenerator uuidGenerator) {
        this.commandHandler = commandHandler;
        this.uuidGenerator = uuidGenerator;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@NotNull @Valid @RequestBody final ServiceRequest service) {

        this.commandHandler.handle(
                new CreateAServiceCommand(
                        uuidGenerator.next(),
                        service.name()
                )
        );
    }

}
