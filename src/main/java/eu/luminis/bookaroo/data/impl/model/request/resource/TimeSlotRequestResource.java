package eu.luminis.bookaroo.data.impl.model.request.resource;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.time.Instant;

public class TimeSlotRequestResource {

    private final DateTimeRequestResource start;
    private final DateTimeRequestResource end;

    static TimeSlotRequestResource of(Instant start, Instant end) {
        return new TimeSlotRequestResource(DateTimeRequestResource.of(start), DateTimeRequestResource.of(end));
    }

    private TimeSlotRequestResource(DateTimeRequestResource start, DateTimeRequestResource end) {
        this.start = start;
        this.end = end;
    }

    @JsonGetter("Start")
    public DateTimeRequestResource getStart() {
        return start;
    }

    @JsonGetter("End")
    public DateTimeRequestResource getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "TimeSlotRequestResource{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
