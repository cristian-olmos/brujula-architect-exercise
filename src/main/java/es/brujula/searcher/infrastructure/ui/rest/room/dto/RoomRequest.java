package es.brujula.searcher.infrastructure.ui.rest.room.dto;

public class RoomRequest {
    private final String name;
    private final Double price;
    private final String occupation;

    public RoomRequest(String name, Double price, String occupation) {
        this.name = name;
        this.price = price;
        this.occupation = occupation;
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
