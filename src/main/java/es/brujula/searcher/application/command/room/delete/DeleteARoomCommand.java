package es.brujula.searcher.application.command.room.delete;

public final class DeleteARoomCommand {

    private final String id;


    public DeleteARoomCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
