package es.brujula.searcher.infrastructure.ui.rest.hotel.dto;

public class AddServiceToHotelRequest {

    private String serviceId;

    public AddServiceToHotelRequest(String serviceId) {
        this.serviceId = serviceId;
    }

    public String serviceId() {
        return serviceId;
    }

}
