package eu.luminis.bookaroo.handlers;

import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;
import eu.luminis.bookaroo.model.Room;
import eu.luminis.bookaroo.model.User;
import eu.luminis.bookaroo.service.AvailabilityService;
import eu.luminis.bookaroo.service.impl.AvailabilityServiceImpl;
import eu.luminis.bookaroo.utils.TimeUtils;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AvailableIntentHandler extends AuthorizedIntentHandler {

    private static final AvailabilityService availabilityService = new AvailabilityServiceImpl();
    private static final Duration MEETING_DURATION = Duration.ofMinutes(30);
    private static final Logger LOGGER = LogManager.getLogger(AvailableIntentHandler.class);

    public AvailableIntentHandler() {
        super("AvailableIntent");
    }

    @Override
    Optional<Response> getResponse(IntentRequest intentRequest, ResponseBuilder responseBuilder) {
        Optional<LocalTime> localTime = extractLocalTime(intentRequest);

        if (!localTime.isPresent()) {
            throw new IllegalStateException("Could not extract time from request");
        }

        Instant instant = TimeUtils.fromLocalTime(localTime.get());

        boolean roomIsAvailable = availabilityService.isAvailable(Room.LARGE, instant, MEETING_DURATION, User.withAccessToken(getAccessToken()));

        String title;
        String text;

        if (roomIsAvailable) {
            title = "You're in luck!";
            text = String.format("You're in luck, the room is available for the next %s minutes.", MEETING_DURATION.toMinutes());
        } else {
            title = "I'm sorry";
            text = String.format("I'm sorry, the room is already taken for the next %s minutes.", MEETING_DURATION.toMinutes());
        }

        return responseBuilder
                .withSpeech(text)
                .withSimpleCard(title, text)
                .build();
    }

    private Optional<LocalTime> extractLocalTime(IntentRequest intentRequest) {
        Map<String, Slot> slots = intentRequest.getIntent().getSlots();

        if (slots == null || slots.isEmpty()) {
            throw new IllegalStateException("Time slot cannot be missing.");
        }

        Slot timeSlot = slots.get("time");

        if (timeSlot == null || timeSlot.getValue().isEmpty()) {
            throw new IllegalStateException("Time slot value cannot be missing.");
        }

        return TimeUtils.fromAmazonTimeValue(timeSlot.getValue());
    }
}
