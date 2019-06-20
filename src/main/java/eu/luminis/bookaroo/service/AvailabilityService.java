package eu.luminis.bookaroo.service;

import eu.luminis.bookaroo.model.Room;
import eu.luminis.bookaroo.model.User;
import java.time.Duration;
import java.time.Instant;

public interface AvailabilityService {

    boolean isAvailable(Room room, Instant instant, Duration duration, User user);
}
