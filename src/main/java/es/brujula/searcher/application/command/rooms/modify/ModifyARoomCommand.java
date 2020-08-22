package es.brujula.searcher.application.command.rooms.modify;

public final class ModifyARoomCommand {

    private final String id;
    private final String idHotel;
    private final String name;
    private final Double price;
    private final String occupation;

    public ModifyARoomCommand(String id, String idHotel, String name, Double price, String occupation) {
        this.id = id;
        this.idHotel = idHotel;
        this.name = name;
        this.price = price;
        this.occupation = occupation;
    }

    public String getId() {
        return id;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getOccupation() {
        return occupation;
    }
}
