package org.example.ordersmanager.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.example.ordersmanager.views.LoginView;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        serviceInitEvent.getSource().addUIInitListener(uiInitEvent -> {
            final UI ui = uiInitEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
                }
        );
    }

    private void authenticateNavigation(BeforeEnterEvent beforeEnterEvent) {
        if (!LoginView.class.equals(beforeEnterEvent.getNavigationTarget())
        && !SecurityUtils.isUserLoggedIn()) {
            beforeEnterEvent.rerouteTo(LoginView.class);
        }
    }
}
