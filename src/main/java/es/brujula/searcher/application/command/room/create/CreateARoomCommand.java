package es.brujula.searcher.application.command.room.create;

public final class CreateARoomCommand {

    private final String id;
    private final String hotelId;
    private final String name;
    private final Double price;
    private final String occupation;

    public CreateARoomCommand(String id, String hotelId, String name, Double price, String occupation) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.price = price;
        this.occupation = occupation;
    }

    public String getId() {
        return id;
    }

    public String getHotelId() {
        return hotelId;
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
