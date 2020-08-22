package es.brujula.searcher.domain.rooms.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public final class Provider implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("documentIdentifier")
    private String documentIdentifier;


    private Provider(String name, String documentIdentifier) {
        this.setName(name);
        this.setDocumentIdentifier(documentIdentifier);
    }

    public static Provider create(String name, String documentIdentifier) {
        return new Provider(name, documentIdentifier);
    }

    private void setName(String name) {
        Validate.notBlank(name);
        this.name = name.trim();
    }

    private void setDocumentIdentifier(String documentIdentifier) {
        Validate.notBlank(documentIdentifier);
        this.documentIdentifier = documentIdentifier.trim();
    }

    public String name() {
        return name;
    }

    public String documentIdentifier() {
        return documentIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Provider provider = (Provider) o;

        if (!name.equals(provider.name)) {
            return false;
        }

        return documentIdentifier.equals(provider.documentIdentifier);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + documentIdentifier.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", documentIdentifier='" + documentIdentifier + '\'' +
                '}';
    }
}

