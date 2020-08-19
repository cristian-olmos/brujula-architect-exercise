package es.brujula.shared;

public interface CommandHandler<C> {
    void handle(final C command);
}
