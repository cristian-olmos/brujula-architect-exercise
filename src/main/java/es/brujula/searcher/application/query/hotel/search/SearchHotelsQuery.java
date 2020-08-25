package es.brujula.searcher.application.query.hotel.search;

import java.util.List;

public final class SearchHotelsQuery {
    String name;
    List<String> categories;
    List<String> services;

    public SearchHotelsQuery(String name, List<String> categories, List<String> services) {
        this.name = name;
        this.categories = categories;
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getServices() {
        return services;
    }
}
