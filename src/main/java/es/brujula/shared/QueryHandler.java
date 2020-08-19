package es.brujula.shared;

public interface QueryHandler<R, Q> {
    R handle(final Q query);
}
