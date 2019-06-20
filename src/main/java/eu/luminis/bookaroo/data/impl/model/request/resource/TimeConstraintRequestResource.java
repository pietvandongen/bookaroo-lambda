package eu.luminis.bookaroo.data.impl.model.request.resource;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class TimeConstraintRequestResource {

    private static final String ACTIVITY_DOMAIN = "Unrestricted";

    private final List<TimeSlotRequestResource> timeSlots;

    public static TimeConstraintRequestResource of(Instant start, Instant end) {
        return new TimeConstraintRequestResource(Collections.singletonList(TimeSlotRequestResource.of(start, end)));
    }

    private TimeConstraintRequestResource(List<TimeSlotRequestResource> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @JsonGetter("ActivityDomain")
    public static String getActivityDomain() {
        return ACTIVITY_DOMAIN;
    }

    @JsonGetter("Timeslots")
    public List<TimeSlotRequestResource> getTimeSlots() {
        return timeSlots;
    }

    @Override
    public String toString() {
        return "TimeConstraintRequestResource{" +
                "timeSlots=" + timeSlots +
                '}';
    }
}
