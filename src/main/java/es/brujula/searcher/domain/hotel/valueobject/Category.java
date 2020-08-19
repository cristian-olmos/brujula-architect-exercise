package es.brujula.searcher.domain.hotel.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Category implements Serializable {

    @JsonProperty("total")
    private Long total;

    private Category(Long total) {
        this.setTotal(total);
    }

    public static Category from(Long total) {
        return new Category(total);
    }

    private void setTotal(Long total) {
        Validate.notNull(total);
        this.total = total;
    }

    public Long total() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Category category = (Category) o;

        return total.equals(category.total);
    }

    @Override
    public int hashCode() {
        return total.hashCode();
    }

    @Override
    public String toString() {
        return "Category{" +
                "total=" + total +
                '}';
    }
}
