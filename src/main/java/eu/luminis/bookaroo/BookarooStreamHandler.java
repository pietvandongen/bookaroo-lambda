package eu.luminis.bookaroo;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.luminis.bookaroo.handlers.AvailableIntentHandler;
import eu.luminis.bookaroo.handlers.ReserveDurationIntentHandler;
import java.time.ZoneId;

public class BookarooStreamHandler extends SkillStreamHandler {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final ZoneId USER_TIME_ZONE_ID = ZoneId.of("CET");

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public BookarooStreamHandler() {
        super(getSkill());
    }

    private static Skill getSkill() {
        return Skills
                .standard()
                .addRequestHandlers(
                        new AvailableIntentHandler(),
                        new ReserveDurationIntentHandler()
                )
                .build();
    }
}
