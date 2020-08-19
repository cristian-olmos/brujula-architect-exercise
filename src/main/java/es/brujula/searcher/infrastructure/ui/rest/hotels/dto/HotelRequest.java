package es.brujula.searcher.infrastructure.ui.rest.hotel.dto;

public class HotelRequest {

    private String name;
    private String address;
    private String category;

    public HotelRequest(String name, String address, String category) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelRequest that = (HotelRequest) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return category != null ? category.equals(that.category) : that.category == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HotelRequest{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
