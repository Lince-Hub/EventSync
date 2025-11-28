package lt.linas_puplauskas.event_sync.domain;

import lombok.Data;

@Data
public class FeedbackRequest {
    private String textFeedback;
    private String username;
}
