package es.brujula.searcher.application.query.service.obtain;

public final class ObtainServiceQuery {

    private final String serviceId;

    public ObtainServiceQuery(String serviceId) {
        this.serviceId = serviceId;
    }

    public String serviceId() {
        return serviceId;
    }
}
