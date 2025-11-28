package lt.linas_puplauskas.event_sync.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.services.EventService;
import lt.linas_puplauskas.event_sync.ui.MainLayout;


@Route(value = "/create/event", layout = MainLayout.class)
public class EventForm extends VerticalLayout {
    private final EventService eventService;

    private final H2 title = new H2("Create Event");
    private final TextField titleTextField = new TextField("Enter Title:");
    private final FormLayout form = new FormLayout();
    private final Button button = new Button("Submit");
    private final Notification notificationSuccess = new Notification("Feedback added!", 3000, Notification.Position.TOP_CENTER);
    private final Notification notificationError = new Notification("Please enter feedback!", 3000, Notification.Position.TOP_CENTER);

    public EventForm(EventService eventService) {
        this.eventService = eventService;
        createUI();
    }

    private void createUI() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);


        form.getStyle().set("margin-left", "auto");
        form.getStyle().set("margin-right", "auto");

        form.add(titleTextField, 2);
        form.add(button);

        button.addClickListener(clickEvent -> {
            String titleText = titleTextField.getValue().trim();
            if (titleText.isEmpty()) {
                notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notificationError.open();
                return;
            }

            try {
                Event event = new Event(titleText);
                eventService.addEvent(event);

                notificationSuccess.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notificationSuccess.open();

                button.getUI().ifPresent(ui -> ui.navigate(""));
            } catch (Exception e) {
                notificationError.setText("Error adding feedback!");
                notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notificationError.open();
            }
        });

        add(title, form);
    }
}
