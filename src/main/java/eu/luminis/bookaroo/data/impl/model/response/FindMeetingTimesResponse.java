package eu.luminis.bookaroo.data.impl.model.response;

import com.fasterxml.jackson.annotation.JsonSetter;
import eu.luminis.bookaroo.data.impl.model.response.resource.MeetingTimeSuggestionResponseResource;
import eu.luminis.bookaroo.model.MeetingTimeSlot;
import java.util.List;
import java.util.stream.Collectors;

public class FindMeetingTimesResponse {

    private String emptySuggestionsReason;

    private List<MeetingTimeSuggestionResponseResource> meetingTimeSuggestions;

    public String getEmptySuggestionsReason() {
        return emptySuggestionsReason;
    }

    @JsonSetter("EmptySuggestionsReason")
    public void setEmptySuggestionsReason(String emptySuggestionsReason) {
        this.emptySuggestionsReason = emptySuggestionsReason;
    }

    public List<MeetingTimeSuggestionResponseResource> getMeetingTimeSuggestions() {
        return meetingTimeSuggestions;
    }

    @JsonSetter("MeetingTimeSuggestions")
    public void setMeetingTimeSuggestions(List<MeetingTimeSuggestionResponseResource> meetingTimeSuggestions) {
        this.meetingTimeSuggestions = meetingTimeSuggestions;
    }

    public List<MeetingTimeSlot> toMeetingTimeSlots() {
        return meetingTimeSuggestions.stream()
                .map(MeetingTimeSuggestionResponseResource::toMeetingTimeSlot)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "FindMeetingTimesResponse{" +
                "emptySuggestionsReason='" + emptySuggestionsReason + '\'' +
                ", meetingTimeSuggestions=" + meetingTimeSuggestions +
                '}';
    }
}
