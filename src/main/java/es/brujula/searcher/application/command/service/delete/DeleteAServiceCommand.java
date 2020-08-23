package es.brujula.searcher.application.command.service.delete;

public final class DeleteAServiceCommand {

    private final String id;


    public DeleteAServiceCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
