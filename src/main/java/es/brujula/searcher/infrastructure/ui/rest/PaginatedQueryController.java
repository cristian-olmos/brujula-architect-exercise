package es.brujula.searcher.infrastructure.ui.rest;

import java.util.HashMap;
import java.util.Map;

public class PaginatedQueryController<T, R> {

    private static final String DATA = "data";
    private static final String PAGE = "page";
    private static final String LIMIT = "limit";

    public Map<String, Object> createResponse(T data, R page, R limit) {

        Map<String, T> responseData = new HashMap<>();
        responseData.put(DATA, data);

        Map<String, R> responsePagination = new HashMap<>();
        responsePagination.put(LIMIT, limit);
        responsePagination.put(PAGE, page);

        Map<String, Object> response = new HashMap<>();
        response.putAll(responseData);
        response.putAll(responsePagination);

        return response;
    }
}
