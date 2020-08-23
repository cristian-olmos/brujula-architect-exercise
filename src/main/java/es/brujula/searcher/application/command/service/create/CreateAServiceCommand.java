package es.brujula.searcher.application.command.service.create;

public final class CreateAServiceCommand {

    private final String id;
    private final String name;

    public CreateAServiceCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
