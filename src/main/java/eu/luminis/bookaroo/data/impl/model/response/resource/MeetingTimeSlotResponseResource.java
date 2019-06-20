package eu.luminis.bookaroo.data.impl.model.response.resource;

import com.fasterxml.jackson.annotation.JsonSetter;

public class MeetingTimeSlotResponseResource {

    private MeetingTimeSlotZonedDateTimeResponseResource start;
    private MeetingTimeSlotZonedDateTimeResponseResource end;

    public MeetingTimeSlotZonedDateTimeResponseResource getStart() {
        return start;
    }

    @JsonSetter("Start")
    public void setStart(MeetingTimeSlotZonedDateTimeResponseResource start) {
        this.start = start;
    }

    public MeetingTimeSlotZonedDateTimeResponseResource getEnd() {
        return end;
    }

    @JsonSetter("End")
    public void setEnd(MeetingTimeSlotZonedDateTimeResponseResource end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "MeetingTimeSlotResponseResource{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
