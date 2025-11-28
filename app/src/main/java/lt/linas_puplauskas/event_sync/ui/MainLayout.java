package lt.linas_puplauskas.event_sync.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Layout;

@Layout
public class MainLayout extends AppLayout {
    public MainLayout() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(new H1("EventSync"));
        horizontalLayout.setWidthFull();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        addToNavbar(horizontalLayout);

        VerticalLayout verticalLayout = new VerticalLayout();
        setContent(verticalLayout);
    }

    public void add(Component component) {
        setContent(component);
    }
}
