package org.example.ordersmanager.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import org.example.ordersmanager.auth.CustomUserDetails;
import org.example.ordersmanager.security.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;

public class MainLayout extends AppLayout {
    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Paragraph logo = new Paragraph("orders manager");

        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Span loggedInUserName = new Span("Hello, " + user.getUsername() + "! ");
//        System.out.println("logged in user is admin: " + user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin")));

        Button logout = new Button("Log out", buttonClickEvent -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, loggedInUserName, logout);
        header.expand(logo);
        header.setWidth("100%");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink));
    }
}
