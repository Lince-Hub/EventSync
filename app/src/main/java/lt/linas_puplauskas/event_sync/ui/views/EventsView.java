package lt.linas_puplauskas.event_sync.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.services.EventService;
import lt.linas_puplauskas.event_sync.ui.MainLayout;
import lt.linas_puplauskas.event_sync.ui.component.EventComponent;


@Route(value = "", layout = MainLayout.class)
public class EventsView extends VerticalLayout {

    private final EventService eventService;
    private final Button createEventButton = new Button("Create Event");

    public EventsView(EventService eventService) {
        this.eventService = eventService;
        createUI();
    }

    public void createUI(){
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setAlignItems(Alignment.CENTER);

        createEventButton.addClickListener(e -> {
            createEventButton.getUI().ifPresent(ui -> ui.navigate("/create/event/"));
        });

        layout.add(new HorizontalLayout(createEventButton));

        for (Event event : eventService.findAllEvents()){
            layout.add(new EventComponent(event));
        }

        add(layout);
    }

}
