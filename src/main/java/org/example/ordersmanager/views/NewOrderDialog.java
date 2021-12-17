package org.example.ordersmanager.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.example.ordersmanager.auth.CustomUserDetails;
import org.example.ordersmanager.data.model.Order;
import org.example.ordersmanager.data.service.ListService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.Date;

public class NewOrderDialog extends Dialog {

    TextField description = new TextField("description");
    TextField title = new TextField("title");

    Button save = new Button("save", event -> saveAndClose());
    Button close = new Button("close", event -> cancelAndClose());

    H1 h1 = new H1("new order!");

    FormLayout formLayout = new FormLayout(description, title, createButtonsLayout());

    public NewOrderDialog() {
        this.setCloseOnOutsideClick(true);
        add(h1, formLayout);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, close);
    }

    public void showOrderDialog() {
        this.open();
    }

    private void saveAndClose(){
        System.out.println(" --> \"save and close\" was clicked! " + description.getValue() + " and " + title.getValue());
        Order newOrder = new Order();
        newOrder.setDescription(description.getValue());
        newOrder.setTitle(title.getValue());
        newOrder.setDate(new Date());
        newOrder.setStatus("new \uD83D\uDE2E\u200D\uD83D\uDCA8");
        newOrder.setSumTotal(BigDecimal.valueOf(0l));

        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newOrder.setUser(user.getUser());

        System.out.println(" ---> " + newOrder.toString());
        // implement the "save to DB" logic here...
        ListService.saveOrder(newOrder);
        this.close();
    }

    private void cancelAndClose() {
        System.out.println(" --> \"cancel and close\" was clicked!");
        this.close();
    }
}
