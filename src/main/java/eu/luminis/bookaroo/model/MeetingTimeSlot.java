package eu.luminis.bookaroo.model;

import java.time.Instant;

public class MeetingTimeSlot {

    private final Instant start;
    private final Instant end;

    public static MeetingTimeSlot of(Instant start, Instant end) {
        return new MeetingTimeSlot(start, end);
    }

    private MeetingTimeSlot(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }
}
