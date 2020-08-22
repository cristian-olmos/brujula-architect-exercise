package es.brujula.searcher.domain.rooms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Objects;

public final class Room implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("occupation")
    private String occupation;

    private Room(String id, String name, Double price, String occupation) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setOccupation(occupation);
    }

    public static Room create(String id, String name, Double price, String occupation) {
        return new Room(id, name, price, occupation);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(name, room.name) &&
                Objects.equals(price, room.price) &&
                Objects.equals(occupation, room.occupation);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", occupation='" + occupation + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, occupation);
    }

}
