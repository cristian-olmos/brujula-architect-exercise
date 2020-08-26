package es.brujula.searcher.infrastructure.ui.rest.service.dto;

import io.swagger.annotations.ApiModelProperty;

public class ServiceRequest {
    @ApiModelProperty
    private final String name;

    public ServiceRequest(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

}
