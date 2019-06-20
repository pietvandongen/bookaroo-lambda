package eu.luminis.bookaroo.data.impl.model.response.resource;

import com.fasterxml.jackson.annotation.JsonSetter;
import eu.luminis.bookaroo.model.MeetingTimeSlot;
import java.util.List;

public class MeetingTimeSuggestionResponseResource {

    private MeetingTimeSlotResponseResource meetingTimeSlot;
    private List<LocationResponseResource> locations;

    public MeetingTimeSlotResponseResource getMeetingTimeSlot() {
        return meetingTimeSlot;
    }

    @JsonSetter("MeetingTimeSlot")
    public void setMeetingTimeSlot(MeetingTimeSlotResponseResource meetingTimeSlot) {
        this.meetingTimeSlot = meetingTimeSlot;
    }

    public List<LocationResponseResource> getLocations() {
        return locations;
    }

    @JsonSetter("Locations")
    public void setLocations(List<LocationResponseResource> locations) {
        this.locations = locations;
    }

    public MeetingTimeSlot toMeetingTimeSlot() {
        return MeetingTimeSlot.of(meetingTimeSlot.getStart().toInstant(), meetingTimeSlot.getEnd().toInstant());
    }

    @Override
    public String toString() {
        return "MeetingTimeSuggestionResponseResource{" +
                "meetingTimeSlot=" + meetingTimeSlot +
                ", locations=" + locations +
                '}';
    }
}
