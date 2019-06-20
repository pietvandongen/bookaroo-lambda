package eu.luminis.bookaroo.data;

import eu.luminis.bookaroo.model.MeetingTimeSlot;
import eu.luminis.bookaroo.model.Room;
import eu.luminis.bookaroo.model.User;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface AvailabilityStore {

    List<MeetingTimeSlot> findMeetingTimes(Room room, Instant instant, Duration duration, User user);
}
