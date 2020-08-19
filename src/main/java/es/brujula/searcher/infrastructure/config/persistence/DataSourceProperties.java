package es.brujula.searcher.infrastructure.config.persistence;

public class DataSourceProperties {

    private Boolean continueOnError;
    private String initializationMode;
    private String jndiName;
    private String schema;

    public Boolean getContinueOnError() {
        return continueOnError;
    }

    public void setContinueOnError(Boolean continueOnError) {
        this.continueOnError = continueOnError;
    }

    public String getInitializationMode() {
        return initializationMode;
    }

    public void setInitializationMode(String initializationMode) {
        this.initializationMode = initializationMode;
    }

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
