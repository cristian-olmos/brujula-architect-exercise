package es.brujula.searcher.application.command.room.modify;

public final class ModifyARoomCommand {

    private final String id;
    private final String hotelId;
    private final String name;
    private final Double price;
    private final String occupation;

    public ModifyARoomCommand(String id, String hotelId, String name, Double price, String occupation) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.price = price;
        this.occupation = occupation;
    }

    public String id() {
        return id;
    }

    public String hotelId() {
        return hotelId;
    }

    public String name() {
        return name;
    }

    public Double price() {
        return price;
    }

    public String occupation() {
        return occupation;
    }

}
