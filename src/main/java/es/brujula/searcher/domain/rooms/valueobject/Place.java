package es.brujula.searcher.domain.rooms.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Place implements Serializable {

    private static final int LIMIT_NUMBER = 19;

    @JsonProperty("total")
    private Long total;

    @JsonProperty("offered")
    private Long offered;

    private Place(Long total, Long offered) {
        this.setTotal(total);
        this.setOffered(offered);
    }

    public static Place create(Long total, Long offered) {
        return new Place(total, offered);
    }

    private void setTotal(Long total) {
        Validate.notNull(total);
        Validate.isTrue(total.toString().length() < LIMIT_NUMBER, "INVALID_HOTEL_TOTAL");

        this.total = total;
    }

    private void setOffered(Long offered) {
        Validate.notNull(offered);
        Validate.isTrue(offered.toString().length() < LIMIT_NUMBER, "INVALID_HOTEL_OFFERED");

        this.offered = offered;
    }

    public Long total() {
        return total;
    }

    public Long offered() {
        return offered;
    }

    @Override
    public String toString() {
        return "Place{" +
                "total=" + total +
                ", offered=" + offered +
                '}';
    }
}
