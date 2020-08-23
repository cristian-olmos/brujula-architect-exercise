package es.brujula.searcher.domain.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Services implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    public Services(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Services create(String id, String name) {
        return new Services(id, name);
    }

    public String id() {
        return id;
    }

    private void setId(String id) {
        Validate.notBlank(id);
        this.id = id.trim();
    }

    public String name() {
        return name;
    }

    private void setName(String name) {
        Validate.notBlank(name);
        this.name = name.trim();
    }

    public void modify(String id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
