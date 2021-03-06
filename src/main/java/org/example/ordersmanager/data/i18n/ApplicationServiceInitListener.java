package org.example.ordersmanager.data.i18n;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

import static java.lang.System.setProperty;

@Component
public class ApplicationServiceInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        setProperty("vaadin.i18n.provider", VaadinI18NProvider.class.getName());
    }
}
