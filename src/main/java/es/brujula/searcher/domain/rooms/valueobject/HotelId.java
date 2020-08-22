package es.brujula.searcher.domain.rooms.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class HotelId implements Serializable {

    private static final int LIMIT_NUMBER = 19;

    @JsonProperty("id")
    private Long id;

    public HotelId(Long id) {
        this.setId(id);
    }

    public void setId(Long id) {
        Validate.notNull(id);
        Validate.isTrue(id.toString().length() < LIMIT_NUMBER, "INVALID_HOTEL_ID");
        this.id = id;
    }

    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return "HotelId{" +
                "id=" + id +
                '}';
    }
}
