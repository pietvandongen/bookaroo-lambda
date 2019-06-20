package eu.luminis.bookaroo.handlers;

import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReserveDurationIntentHandler extends AuthorizedIntentHandler {

    private static final Logger LOGGER = LogManager.getLogger(ReserveDurationIntentHandler.class);

    public ReserveDurationIntentHandler() {
        super("ReserveDurationIntent");
    }

    @Override
    Optional<Response> getResponse(IntentRequest intentRequest, ResponseBuilder responseBuilder) {
        String title = "Debug title!";
        String text = "This is a placeholder response.";

        return responseBuilder
                .withSpeech(text)
                .withSimpleCard(title, text)
                .build();
    }
}
