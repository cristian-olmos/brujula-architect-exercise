package es.brujula.searcher.domain.rooms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Room implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("category")
    private String category;

    private Room(String id, String name, String address, String category) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setCategory(category);
    }

    public static Room create(String id, String name, String address, String category) {
        return new Room(id, name, address, category);
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

    public String address() {
        return address;
    }

    private void setAddress(String address) {
        Validate.notBlank(address);
        this.address = address.trim();
    }

    public String category() {
        return category;
    }

    private void setCategory(String category) {
        Validate.notBlank(category);
        this.category = category.trim();
    }

    public void modify(String name, String address, String category) {
        this.setName(name);
        this.setAddress(address);
        this.setCategory(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room hotel = (Room) o;

        if (id != null ? !id.equals(hotel.id) : hotel.id != null) return false;
        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        if (address != null ? !address.equals(hotel.address) : hotel.address != null) return false;
        return category != null ? category.equals(hotel.category) : hotel.category == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
