package eu.luminis.bookaroo.data.impl.model.request;

import com.fasterxml.jackson.annotation.JsonGetter;
import eu.luminis.bookaroo.data.impl.model.request.resource.LocationConstraintRequestResource;
import eu.luminis.bookaroo.data.impl.model.request.resource.TimeConstraintRequestResource;
import eu.luminis.bookaroo.model.Room;
import java.time.Instant;

public class FindMeetingTimesRequest {

    private static final boolean RETURN_SUGGESTION_REASONS = true;

    private final LocationConstraintRequestResource locationConstraint;
    private final TimeConstraintRequestResource timeConstraint;

    public static FindMeetingTimesRequest of(Room room, Instant start, Instant end) {
        return new FindMeetingTimesRequest(LocationConstraintRequestResource.of(room), TimeConstraintRequestResource.of(start, end));
    }

    private FindMeetingTimesRequest(LocationConstraintRequestResource locationConstraint, TimeConstraintRequestResource timeConstraint) {
        this.locationConstraint = locationConstraint;
        this.timeConstraint = timeConstraint;
    }

    @JsonGetter("ReturnSuggestionReasons")
    public boolean returnSuggestionReasons() {
        return RETURN_SUGGESTION_REASONS;
    }

    @JsonGetter("LocationConstraint")
    public LocationConstraintRequestResource getLocationConstraint() {
        return locationConstraint;
    }

    @JsonGetter("TimeConstraint")
    public TimeConstraintRequestResource getTimeConstraint() {
        return timeConstraint;
    }

    @Override
    public String toString() {
        return "FindMeetingTimesRequest{" +
                "locationConstraint=" + locationConstraint +
                ", timeConstraint=" + timeConstraint +
                '}';
    }
}
