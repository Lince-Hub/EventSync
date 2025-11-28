package lt.linas_puplauskas.event_sync.repository;

import lt.linas_puplauskas.event_sync.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
