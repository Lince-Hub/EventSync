package lt.linas_puplauskas.event_sync.ui.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.services.EventService;
import lt.linas_puplauskas.event_sync.ui.MainLayout;
import lt.linas_puplauskas.event_sync.ui.component.SummaryComponent;

@Route(value = "/summary/", layout = MainLayout.class)
public class SummaryView extends VerticalLayout implements HasUrlParameter<Integer> {

    private Event event;
    private final EventService eventService;

    public SummaryView(EventService eventService) {
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

        H2 title = new H2("Summary for: " + event.getTitle());
        add(title);

        add(new SummaryComponent(eventService.getRatings(event)));
    }
}
