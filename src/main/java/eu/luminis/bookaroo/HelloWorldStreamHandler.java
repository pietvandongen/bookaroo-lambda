package eu.luminis.bookaroo;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import eu.luminis.bookaroo.handlers.CancelandStopIntentHandler;
import eu.luminis.bookaroo.handlers.FallbackIntentHandler;
import eu.luminis.bookaroo.handlers.HelloWorldIntentHandler;
import eu.luminis.bookaroo.handlers.HelpIntentHandler;
import eu.luminis.bookaroo.handlers.LaunchRequestHandler;
import eu.luminis.bookaroo.handlers.SessionEndedRequestHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }
}
