package eu.luminis.bookaroo.utils;

import eu.luminis.bookaroo.BookarooStreamHandler;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class TimeUtils {

    private static final Logger LOGGER = LogManager.getLogger(TimeUtils.class);

    private enum AmazonTime {
        NI(LocalTime.MIDNIGHT),
        MO(LocalTime.of(6, 0)),
        AF(LocalTime.NOON),
        EV(LocalTime.of(18, 0));

        private final LocalTime localTime;

        AmazonTime(LocalTime localTime) {
            this.localTime = localTime;
        }

        public LocalTime getLocalTime() {
            return localTime;
        }
    }

    private TimeUtils() {
        throw new AssertionError("Util class should not be instantiated");
    }

    public static Optional<LocalTime> fromAmazonTimeValue(String amazonTimeValue) {
        return Optional.ofNullable(TimeUtils.fromIsoString(amazonTimeValue)
                .orElseGet(() -> TimeUtils.fromTimePeriod(amazonTimeValue)
                        .orElse(null)));
    }

    public static Instant fromLocalTime(LocalTime localTime) {
        return localTime
                .atDate(LocalDate.now())
                .atZone(BookarooStreamHandler.USER_TIME_ZONE_ID)
                .toInstant();
    }

    public LocalTime getPreviousStartOfQuarterOfAnHour(LocalTime time) {
        return time
                .truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(15 * (((long) time.getMinute()) / 15));
    }

    public static Instant getStartOfLastHourQuarter(Instant instant) {
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));

        return zonedDateTime
                .truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(15 * (((long) zonedDateTime.getMinute()) / 15))
                .toInstant();
    }

    private static Optional<LocalTime> fromIsoString(String timeIsoString) {
        try {
            return Optional.of(LocalTime.parse(timeIsoString));
        } catch (DateTimeParseException e) {
            LOGGER.info("Could not parse time slot value, Time-Iso-String={}", timeIsoString);
        }

        return Optional.empty();
    }

    private static Optional<LocalTime> fromTimePeriod(String timePeriodString) {
        try {
            return Optional.of(AmazonTime.valueOf(timePeriodString).getLocalTime());
        } catch (DateTimeParseException e) {
            LOGGER.info("Could not parse time period string, Time-Period-String={}", timePeriodString);
        }

        return Optional.empty();
    }
}
