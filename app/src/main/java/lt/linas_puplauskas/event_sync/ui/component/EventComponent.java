package lt.linas_puplauskas.event_sync.ui.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.card.CardVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lt.linas_puplauskas.event_sync.domain.Event;
import lt.linas_puplauskas.event_sync.domain.Feedback;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventComponent extends Card {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final VerticalLayout feedbackLayout = new VerticalLayout();

    public EventComponent(Event event) {
        List<Feedback> feedbacks = event.getFeedbacks();
        H3 title = new H3(event.getTitle());

        this.setWidth("500px");
        this.addThemeVariants(CardVariant.LUMO_ELEVATED);

        for(Feedback feedback : feedbacks) {
            VerticalLayout layout = new VerticalLayout();

            Span feedbackSpan = new Span("Feedback " + feedback.getId() + ": ");
            Span ratingSpan = new Span("Rating: ");
            Span dateSpan = new Span("Date & Time: ");
            Span userSpan = new Span("User: ");
            setSpanStyles(feedbackSpan, ratingSpan, dateSpan, userSpan);

            layout.add(new HorizontalLayout(feedbackSpan, new Div(feedback.getTextFeedback())));
            layout.add(new HorizontalLayout(ratingSpan, new Div(feedback.getRating())));
            layout.add(new HorizontalLayout(dateSpan, new Div(feedback.getDate().format(formatter))));
            layout.add(new HorizontalLayout(userSpan, new Div(feedback.getUsername())));

            feedbackLayout.add(layout);
        }

        Button addFeedbackButton = new Button("Add Feedback");
        addFeedbackButton.addClickListener(clickEvent -> {
            addFeedbackButton.getUI().ifPresent(ui -> ui.navigate("/create/feedback/"+event.getId()));
        });

        Button summaryButton = new Button("Summary");
        summaryButton.addClickListener(clickEvent -> {
            summaryButton.getUI().ifPresent(ui -> ui.navigate("/summary/"+event.getId()));
        });

        feedbackLayout.add(new HorizontalLayout(addFeedbackButton, summaryButton));

        this.add(title, feedbackLayout);
    }

    private void setSpanStyles(Span span1, Span span2, Span span3, Span span4) {
        span1.getStyle().setFontWeight(700);
        span2.getStyle().setFontWeight(700);
        span3.getStyle().setFontWeight(700);
        span4.getStyle().setFontWeight(700);
    }
}
