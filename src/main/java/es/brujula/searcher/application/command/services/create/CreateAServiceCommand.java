package es.brujula.searcher.application.command.services.create;

public final class CreateAServiceCommand {

    private final String id;
    private final String name;

    public CreateAServiceCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
