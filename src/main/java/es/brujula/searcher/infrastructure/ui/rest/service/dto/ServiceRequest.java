package es.brujula.searcher.infrastructure.ui.rest.service.dto;

public class ServiceRequest {

    private final String name;

    public ServiceRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
