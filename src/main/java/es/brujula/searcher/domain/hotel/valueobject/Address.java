package es.brujula.searcher.domain.hotel.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Address implements Serializable {

    @JsonProperty("province")
    private Long province;

    @JsonProperty("city")
    private Long city;

    private Address(Long province, Long city) {
        this.setProvince(province);
        this.setCity(city);
    }

    public static Address create(Long province, Long city) {
        return new Address(province, city);
    }

    public Long province() {
        return province;
    }

    private void setProvince(Long province) {
        Validate.notNull(province, "INVALID_PROVINCE");

        this.province = province;
    }

    public Long city() {
        return city;
    }

    private void setCity(Long city) {
        Validate.notNull(city, "INVALID_CITY");

        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }


        final Address address = (Address) o;

        if (!province.equals(address.province)) {
            return false;
        }
        return city.equals(address.city);
    }

    @Override
    public int hashCode() {
        int result = province.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province=" + province +
                ", city=" + city +
                '}';
    }
}
