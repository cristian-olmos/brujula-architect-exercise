package es.brujula.searcher.application.command.hotels.addservice;

public final class AddServiceToHotelCommand {

    private final String hotelId;
    private final String serviceId;

    public AddServiceToHotelCommand(String hotelId, String serviceId) {
        this.hotelId = hotelId;
        this.serviceId = serviceId;
    }

    public String hotelId() {
        return hotelId;
    }

    public String serviceId() {
        return serviceId;
    }
}
