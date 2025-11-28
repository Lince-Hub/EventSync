package lt.linas_puplauskas.event_sync.ui.component;

import com.vaadin.flow.component.card.Card;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lt.linas_puplauskas.event_sync.domain.Rating;

public class SummaryComponent extends Card {

    private final Rating rating;

    public SummaryComponent(Rating rating) {
        this.rating = rating;
        createComponent();
    }

    private void createComponent(){
        VerticalLayout layout = new VerticalLayout();
        Span positiveSpan = new Span("Positive Rating");
        Span neutralSpan = new Span("Neutral Rating");
        Span negativeSpan = new Span("Negative Rating");

        layout.add(new HorizontalLayout(positiveSpan, new Div(String.valueOf(rating.getPositive()))));
        layout.add(new HorizontalLayout(neutralSpan, new Div(String.valueOf(rating.getNeutral()))));
        layout.add(new HorizontalLayout(negativeSpan, new Div(String.valueOf(rating.getNegative()))));
        add(layout);
    }
}
