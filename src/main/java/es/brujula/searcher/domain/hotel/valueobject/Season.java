package es.brujula.searcher.domain.hotel.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Season implements Serializable {

    @JsonProperty("fromYear")
    private Long fromYear;

    private Season(Long fromYear) {
        this.setFromYear(fromYear);
    }

    public static Season from(Long fromYear) {
        return new Season(fromYear);
    }

    public Long fromYear() {
        return fromYear;
    }

    private void setFromYear(Long fromYear) {
        Validate.notNull(fromYear, "INVALID_FROM_YEAR");
        this.fromYear = fromYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Season season = (Season) o;

        return fromYear.equals(season.fromYear);
    }

    @Override
    public int hashCode() {
        return fromYear.hashCode();
    }

    @Override
    public String toString() {
        return "Season{" +
                "fromYear=" + fromYear +
                '}';
    }
}
