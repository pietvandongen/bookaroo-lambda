package eu.luminis.bookaroo.data.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import eu.luminis.bookaroo.BookarooStreamHandler;
import eu.luminis.bookaroo.data.AvailabilityStore;
import eu.luminis.bookaroo.data.impl.model.request.FindMeetingTimesRequest;
import eu.luminis.bookaroo.data.impl.model.response.FindMeetingTimesResponse;
import eu.luminis.bookaroo.model.MeetingTimeSlot;
import eu.luminis.bookaroo.model.Room;
import eu.luminis.bookaroo.model.User;
import eu.luminis.bookaroo.utils.TimeUtils;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class AvailabilityStoreImpl implements AvailabilityStore {

    private static final ObjectWriter REQUEST_BODY_WRITER = BookarooStreamHandler.OBJECT_MAPPER.writerFor(FindMeetingTimesRequest.class);
    private static final ObjectReader RESPONSE_BODY_READER = BookarooStreamHandler.OBJECT_MAPPER.readerFor(FindMeetingTimesResponse.class);

    @Override
    public List<MeetingTimeSlot> findMeetingTimes(Room room, Instant instant, Duration duration, User user) {
        Instant start = TimeUtils.getStartOfLastHourQuarter(instant);
        Instant end = start.plus(duration);
        FindMeetingTimesRequest findMeetingTimesRequest = FindMeetingTimesRequest.of(Room.LARGE, start, end);
        String requestBody;

        try {
            requestBody = REQUEST_BODY_WRITER.writeValueAsString(findMeetingTimesRequest);
        } catch (JsonProcessingException e) {
            // @todo
            throw new IllegalStateException();
        }

        String response;

        try {
            response = Request
                    .Post("https://outlook.office.com/api/v2.0/me/findmeetingtimes")
                    .addHeader("Authorization", "Bearer " + user.getAccessToken())
                    .bodyString(requestBody, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString();
        } catch (IOException e) {
            // @todo
            throw new IllegalStateException();
        }

        FindMeetingTimesResponse findMeetingTimesResponse;

        try {
            findMeetingTimesResponse = RESPONSE_BODY_READER.readValue(response);
        } catch (IOException e) {
            // @todo
            throw new IllegalStateException();
        }

        return findMeetingTimesResponse.toMeetingTimeSlots();
    }
}
