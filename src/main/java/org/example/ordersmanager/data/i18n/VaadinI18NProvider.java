package org.example.ordersmanager.data.i18n;

import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;
import static java.util.ResourceBundle.getBundle;

@Service
public class VaadinI18NProvider implements I18NProvider {

    public static final String RESOURCE_BUNDLE_NAME = "vaadinapp";

    private static final ResourceBundle RESOURCE_BUNDLE_EN = getBundle(RESOURCE_BUNDLE_NAME , ENGLISH);
    private static final ResourceBundle RESOURCE_BUNDLE_DE = getBundle(RESOURCE_BUNDLE_NAME , GERMAN);
    private static final List<Locale> providedLocales = unmodifiableList(asList(ENGLISH , GERMAN));

    @Override
    public List<Locale> getProvidedLocales() {
        return providedLocales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        ResourceBundle resourceBundle;
        if (VaadinSession.getCurrent().getLocale().equals(Locale.ENGLISH)) {
            resourceBundle = RESOURCE_BUNDLE_EN;
        } else {
            resourceBundle = RESOURCE_BUNDLE_DE;
        }
        return (resourceBundle.containsKey(key)) ? resourceBundle.getString(key) : key;
    }
}
