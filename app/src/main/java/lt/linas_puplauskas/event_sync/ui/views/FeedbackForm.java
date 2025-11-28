package lt.linas_puplauskas.event_sync.ui.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.domain.Feedback;
import lt.linas_puplauskas.event_sync.services.EventService;
import lt.linas_puplauskas.event_sync.ui.MainLayout;

@Route(value = "/create/feedback", layout = MainLayout.class)
public class FeedbackForm extends VerticalLayout implements HasUrlParameter<Integer> {

    private Event event;
    private final EventService eventService;

    private final TextField feedbackTextField = new TextField("Insert feedback");
    private final TextField usernameTextField = new TextField("Username");
    private final FormLayout form = new FormLayout();
    private final Button button = new Button("Submit");
    private final Notification notificationSuccess = new Notification("Feedback added!", 3000, Notification.Position.TOP_CENTER);
    private final Notification notificationError = new Notification("Please enter feedback!", 3000, Notification.Position.TOP_CENTER);

    public FeedbackForm(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer eventId) {
        this.event = eventService.findEventById(eventId);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        createUI();
    }

    private void createUI() {
        removeAll();
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        H2 title = new H2("Add feedback for: " + event.getTitle());

        form.getStyle().set("margin-left", "auto");
        form.getStyle().set("margin-right", "auto");

        form.add(feedbackTextField, 2);
        form.add(usernameTextField, 2);
        form.add(button);

        button.addClickListener(clickEvent -> {
            String feedbackText = feedbackTextField.getValue().trim();
            String username = usernameTextField.getValue().trim();

            if (feedbackText.isEmpty() || username.isEmpty()) {
                notificationError.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notificationError.open();
                return;
            }

            try {
                Feedback feedback = new Feedback(feedbackText,  username);
                eventService.addFeedback(event.getId(), feedback);

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
