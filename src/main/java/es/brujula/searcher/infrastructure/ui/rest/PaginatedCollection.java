package es.brujula.searcher.infrastructure.ui.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaginatedCollection implements Serializable {

    @JsonProperty("data")
    private Collection<Serializable> data;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("totalElements")
    private Integer totalElements;

    @JsonCreator
    public PaginatedCollection(Integer page, Integer limit, Integer totalElements, Collection<Serializable> data) {
        this.page = page;
        this.limit = limit;
        this.totalElements = totalElements;
        this.data = data;
    }

    public Integer page() {
        return page;
    }

    public Integer limit() {
        return limit;
    }

    public Integer totalElements() {
        return totalElements;
    }

    public Collection<Serializable> data() {
        return data;
    }

    public Integer totalPages() {
        return this.totalElements / this.page;
    }

    public Integer lastPage() {
        return this.totalPages() - 1;
    }

    public Integer previousPage() {
        return this.page > 0 ? this.page - 1 : 0;
    }

    public Boolean hasPreviousPage() {
        return this.page > 0;
    }

    public Integer nextPage() {
        return this.page < this.lastPage() ? this.page + 1 : this.page;
    }

    public Boolean hasNextPage() {
        return this.page < this.lastPage();
    }

    public Map<String, Object> toPaginated() {
        Map<String, Object> toPaginated = new HashMap<>();

        toPaginated.put("toPaginated", this.data);
        toPaginated.put("meta", this.metadata());

        return toPaginated;
    }

    public Map<String, Integer> metadata() {
        Map<String, Integer> metadata = new HashMap<>();

        metadata.put("page", this.page);
        metadata.put("totalPages", this.totalPages());
        metadata.put("totalElements", this.totalElements);
        metadata.put("limit", this.limit);

        return metadata;
    }

    @Override
    public String toString() {
        return "PaginatedCollection{" +
                "data=" + data +
                ", page=" + page +
                ", limit=" + limit +
                ", totalElements=" + totalElements +
                '}';
    }
}
