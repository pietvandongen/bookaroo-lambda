package eu.luminis.bookaroo.data.impl.model.request.resource;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeRequestResource {

    private static final String ZONE_ID = "UTC";

    private final Instant instant;

    static DateTimeRequestResource of(Instant instant) {
        return new DateTimeRequestResource(instant);
    }

    private DateTimeRequestResource(Instant instant) {
        this.instant = instant;
    }

    @JsonGetter("DateTime")
    public String getInstant() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(instant.atZone(ZoneId.of(ZONE_ID)));
    }

    @JsonGetter("TimeZone")
    public String getTimeZone() {
        return ZONE_ID;
    }

    @Override
    public String toString() {
        return "DateTimeRequestResource{" +
                "instant=" + instant +
                '}';
    }
}
