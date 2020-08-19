package es.brujula.searcher.infrastructure.config.i18n;

import org.apache.commons.lang.CharEncoding;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
@Import(I18nProperties.class)
public class I18nConfig {

    /**
     * Config messageSource properties. Set files location and default encoding.
     * To reload properties after a change in the file use ReloadableResourceBundleMessageSource.
     *
     * @return MessageSource configured
     */
    @Bean
    public MessageSource messageSource(final I18nProperties properties) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(properties.getBaseName());
        messageSource.setDefaultEncoding(CharEncoding.UTF_8);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver(final I18nProperties properties) {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(new Locale(properties.getLocale().getDefaultValue()));
        slr.setSupportedLocales(properties.getLocale().getSupported());
        return slr;
    }
}
