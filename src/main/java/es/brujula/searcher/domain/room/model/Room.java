package es.brujula.searcher.domain.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Room implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonIgnore
    private String hotelId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("occupation")
    private String occupation;

    public Room(String id, String hotelId, String name, Double price, String occupation) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.price = price;
        this.occupation = occupation;
    }

    public static Room create(String id, String hotelId, String name, Double price, String occupation) {
        return new Room(id, hotelId, name, price, occupation);
    }

    public String id() {
        return id;
    }

    private void setId(String id) {
        Validate.notBlank(id);
        this.id = id.trim();
    }

    public String hotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String name() {
        return name;
    }

    private void setName(String name) {
        Validate.notBlank(name);
        this.name = name.trim();
    }

    public Double price() {
        return price;
    }

    private void setPrice(Double price) {
        Validate.isTrue(price > 0.0);
        this.price = price;
    }

    public String occupation() {
        return occupation;
    }

    private void setOccupation(String occupation) {
        Validate.notBlank(occupation);
        this.occupation = occupation.trim();
    }

    public void modify(String name, Double price, String occupation) {
        this.setName(name);
        this.setPrice(price);
        this.setOccupation(occupation);
    }

}
