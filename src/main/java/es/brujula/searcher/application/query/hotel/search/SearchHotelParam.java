package es.brujula.searcher.application.query.hotel.search;

import java.util.List;

public class SearchHotelParam {

    private static final char WILDCARD = '%';
    private String name;
    private List<String> categories;
    private List<String> services;

    public SearchHotelParam(String name, List<String> categories, List<String> services) {
        this.name = name;
        this.categories = categories;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public String getNameWildcard() {
        return WILDCARD + name + WILDCARD;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getServices() {
        return services;
    }
}
