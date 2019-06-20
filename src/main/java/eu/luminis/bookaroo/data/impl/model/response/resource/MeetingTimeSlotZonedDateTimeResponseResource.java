package eu.luminis.bookaroo.data.impl.model.response.resource;

import com.fasterxml.jackson.annotation.JsonSetter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class MeetingTimeSlotZonedDateTimeResponseResource {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");

    private String dateTime;
    private String timeZone;

    public String getDateTime() {
        return dateTime;
    }

    @JsonSetter("DateTime")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @JsonSetter("TimeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Instant toInstant() {
        return LocalDateTime
                .parse(dateTime, DATE_TIME_FORMATTER)
                .atZone(ZoneId.of(timeZone))
                .toInstant();
    }

    @Override
    public String toString() {
        return "MeetingTimeSlotZonedDateTimeResponseResource{" +
                "dateTime='" + dateTime + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
