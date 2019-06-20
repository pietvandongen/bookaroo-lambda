package eu.luminis.bookaroo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.User;
import static com.amazon.ask.request.Predicates.intentName;
import com.amazon.ask.response.ResponseBuilder;
import java.util.Optional;

abstract class AuthorizedIntentHandler implements RequestHandler {

    private final String intentName;

    private String accessToken;

    AuthorizedIntentHandler(String intentName) {
        this.intentName = intentName;
    }

    abstract Optional<Response> getResponse(IntentRequest intentRequest, ResponseBuilder responseBuilder);

    @Override
    public final boolean canHandle(HandlerInput input) {
        return input.matches(intentName(intentName));
    }

    @Override
    public final Optional<Response> handle(HandlerInput input) {
        Optional<String> accessTokenFromInput = extractAccessToken(input);

        if (!accessTokenFromInput.isPresent()) {
            String title = "I'm sorry";
            String text = "I'm sorry, you're not authorized to do this.";

            return input
                    .getResponseBuilder()
                    .withSpeech(text)
                    .withSimpleCard(title, text)
                    .build();
        }

        accessToken = accessTokenFromInput.get();

        return getResponse((IntentRequest) input.getRequestEnvelope().getRequest(), input.getResponseBuilder());
    }

    private Optional<String> extractAccessToken(HandlerInput input) {
        User user = input
                .getRequestEnvelope()
                .getContext()
                .getSystem()
                .getUser();

        if (user == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(user.getAccessToken());
    }

    String getAccessToken() {
        if (accessToken == null) {
            throw new IllegalStateException("Access token cannot be null.");
        }

        return accessToken;
    }
}
