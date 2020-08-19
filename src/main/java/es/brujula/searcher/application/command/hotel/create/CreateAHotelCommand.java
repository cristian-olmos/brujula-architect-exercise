package es.brujula.searcher.application.command.hotel.create;

public final class CreateAHotelCommand {

    private final String id;
    private final String name;
    private final String address;
    private final String category;

    public CreateAHotelCommand(String id, String name, String address, String category) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
    }

    public String name() {
        return name;
    }

    public String address() {
        return address;
    }

    public String category() {
        return category;
    }

    public String id(){
        return id;
    }
}
