package es.brujula.searcher.application.query;

import java.util.Map;

public final class PaginatedSearchParam {

    private Integer page;
    private Integer limit;
    private Map<String, String> filter;
    private String sort;

    private PaginatedSearchParam(Integer page, Integer limit, Map<String, String> filter, String sort) {
        this.page = page;
        this.limit = limit;
        this.filter = filter;
        this.sort = sort;
    }

    public static PaginatedSearchParam create(Integer page, Integer limit, Map<String, String> filter, String sort) {
        return new PaginatedSearchParam(page, limit, filter, sort);
    }

    public Integer page() {
        return page;
    }

    public Integer limit() {
        return limit;
    }

    public Map<String, String> filters() {
        return filter;
    }

    public String sort() {
        return sort;
    }
}
