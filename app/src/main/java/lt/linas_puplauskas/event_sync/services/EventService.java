package lt.linas_puplauskas.event_sync.services;

import lombok.AllArgsConstructor;
import lt.linas_puplauskas.event_sync.constants.Constants;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.domain.Feedback;
import lt.linas_puplauskas.event_sync.domain.Rating;
import lt.linas_puplauskas.event_sync.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    EventRepository repository;

    public List<Event> findAllEvents() {
        return repository.findAll();
    }

    public void addEvent(Event event) {
        repository.save(event);
    }

    public Event findEventById(Integer eventId) {
        return repository.findById(eventId).orElse(null);
    }

    public void addFeedback(Integer eventId, Feedback feedback) {
        Event event = findEventById(eventId);
        event.getFeedbacks().add(feedback);
        repository.save(event);
    }

    public Rating getRatings(Event event){
        int positive = (int) event.getFeedbacks().stream()
                .filter(f -> f.getRating().equals(Constants.POSITIVE))
                .count();

        int neutral = (int) event.getFeedbacks().stream()
                .filter(f -> f.getRating().equals(Constants.NEUTRAL))
                .count();

        int negative = (int) event.getFeedbacks().stream()
                .filter(f -> f.getRating().equals(Constants.NEGATIVE))
                .count();

        return new Rating(positive, neutral, negative);
    }
}
