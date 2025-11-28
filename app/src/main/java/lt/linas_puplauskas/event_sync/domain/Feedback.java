package lt.linas_puplauskas.event_sync.domain;

import jakarta.persistence.*;
import lombok.*;
import lt.linas_puplauskas.event_sync.services.AISentiment;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String textFeedback;
    private String username;
    private String rating;
    private LocalDateTime date = LocalDateTime.now();

    public Feedback(String textFeedback, String username) throws Exception {
        this.textFeedback = textFeedback;
        this.username = username;
        this.rating = AISentiment.analyze(textFeedback);
        this.date = LocalDateTime.now();
    }

}

