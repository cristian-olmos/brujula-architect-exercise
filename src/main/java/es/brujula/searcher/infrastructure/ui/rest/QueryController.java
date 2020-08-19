package es.brujula.searcher.infrastructure.ui.rest;

import java.util.HashMap;
import java.util.Map;

public class QueryController<T> {

    private static final String DATA = "data";

    public Map<String, T> createResponse(T data) {
        Map<String, T> responseEntityMap = new HashMap<>();
        responseEntityMap.put(DATA, data);

        return responseEntityMap;
    }
}
