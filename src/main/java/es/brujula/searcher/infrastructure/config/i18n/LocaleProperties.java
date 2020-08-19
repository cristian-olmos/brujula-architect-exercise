package es.brujula.searcher.infrastructure.config.i18n;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LocaleProperties {

    private List<Locale> supported;
    private String defaultValue;

    public List<Locale> getSupported() {
        return supported;
    }

    public void setSupported(String[] input) {
        supported = Arrays.stream(input).map(Locale::new).collect(Collectors.toList());
    }

    public void setSupported(List<Locale> supported) {
        this.supported = supported;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
