package es.brujula.searcher.application.command.hotels.delete;

public final class DeleteAHotelCommand {

    private final String id;


    public DeleteAHotelCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
