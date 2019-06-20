package eu.luminis.bookaroo.service.impl;

import eu.luminis.bookaroo.data.AvailabilityStore;
import eu.luminis.bookaroo.data.impl.AvailabilityStoreImpl;
import eu.luminis.bookaroo.model.MeetingTimeSlot;
import eu.luminis.bookaroo.model.Room;
import eu.luminis.bookaroo.model.User;
import eu.luminis.bookaroo.service.AvailabilityService;
import eu.luminis.bookaroo.utils.TimeUtils;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Predicate;

public class AvailabilityServiceImpl implements AvailabilityService {

    private static final AvailabilityStore availabilityStore = new AvailabilityStoreImpl();

    @Override
    public boolean isAvailable(Room room, Instant instant, Duration duration, User user) {
        List<MeetingTimeSlot> meetingTimes = availabilityStore.findMeetingTimes(room, instant, duration, user);

        if (meetingTimes.isEmpty()) {
            return false;
        }

        Instant start = TimeUtils.getStartOfLastHourQuarter(instant);
        Instant end = start.plus(duration);

        return meetingTimes.stream()
                .anyMatch(meetingTimeSlotFits(start, end));
    }

    private Predicate<MeetingTimeSlot> meetingTimeSlotFits(Instant start, Instant end) {
        return meetingTimeSlot ->
                (meetingTimeSlot.getStart().equals(start) || meetingTimeSlot.getStart().isBefore(start))
                        && (meetingTimeSlot.getEnd().equals(end) || meetingTimeSlot.getEnd().isAfter(end));
    }
}
