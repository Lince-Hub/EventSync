package lt.linas_puplauskas.event_sync.configuration;

import lombok.AllArgsConstructor;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.domain.Feedback;
import lt.linas_puplauskas.event_sync.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        if(eventRepository.count() == 0) {
            Event event1 = new Event("Event 1");
            event1.getFeedbacks().add(new Feedback("Great event!", "User 1"));
            event1.getFeedbacks().add(new Feedback("The event was okay overall", "User 2"));

            Event event2 = new Event("Event 2");
            event2.getFeedbacks().add(new Feedback("Loved it!", "User 3"));

            Event event3 = new Event("Event 3");
            event3.getFeedbacks().add(new Feedback("The event was average", "User 1"));
            event3.getFeedbacks().add(new Feedback("Too short", "User 3"));

            eventRepository.saveAll(List.of(event1, event2, event3));
        }
    }
}
