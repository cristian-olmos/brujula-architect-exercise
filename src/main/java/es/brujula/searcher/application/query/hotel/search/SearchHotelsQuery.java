package es.brujula.searcher.application.query.hotel.search;

import java.util.Map;

public final class SearchHotelsQuery {

    private Integer page;
    private Integer limit;
    private Map<String, String> filters;
    private String sort;

    public SearchHotelsQuery(Integer page, Integer limit, Map<String, String> filters, String sort) {
        this.page = page;
        this.limit = limit;
        this.filters = filters;
        this.sort = sort;
    }

    public Integer page() {
        return page;
    }

    public Integer limit() {
        return limit;
    }

    public Map<String, String> filters() {
        return filters;
    }

    public String sort() {
        return sort;
    }
}
