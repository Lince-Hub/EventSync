package lt.linas_puplauskas.event_sync.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rating {
    Integer positive;
    Integer neutral;
    Integer negative;
}
