package lt.linas_puplauskas.event_sync.controller;

import lombok.AllArgsConstructor;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.domain.Feedback;
import lt.linas_puplauskas.event_sync.domain.FeedbackRequest;
import lt.linas_puplauskas.event_sync.domain.Rating;
import lt.linas_puplauskas.event_sync.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/")
    public void createEvent(@RequestBody Event event) {
        event.setFeedbacks(new ArrayList<>());
        eventService.addEvent(event);
    }

    @GetMapping("/")
    public List<Event> getEvents() {
        return eventService.findAllEvents();
    }

    @PostMapping("/{eventId}/feedback")
    public void submitFeedback(@PathVariable Integer eventId,
                               @RequestBody FeedbackRequest feedbackRequest) throws Exception {
        Feedback feedback = new Feedback(feedbackRequest.getTextFeedback(), feedbackRequest.getUsername());
        eventService.addFeedback(eventId, feedback);
    }

    @GetMapping("/{eventId}/summary")
    public Rating getFeedbacks(@PathVariable Integer eventId) {
        Event event = eventService.findEventById(eventId);
        return eventService.getRatings(event);
    }
}
