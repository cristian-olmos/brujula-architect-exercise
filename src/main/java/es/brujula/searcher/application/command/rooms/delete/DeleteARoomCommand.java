package es.brujula.searcher.application.command.rooms.delete;

public final class DeleteARoomCommand {

    private final String id;


    public DeleteARoomCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
