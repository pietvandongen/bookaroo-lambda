package eu.luminis.bookaroo.data.impl.model.request.resource;

import com.fasterxml.jackson.annotation.JsonGetter;
import eu.luminis.bookaroo.model.Room;

public class LocationRequestResource {

    private static final boolean RESOLVE_AVAILABILITY = true;

    private final String locationEmailAddress;

    static LocationRequestResource of(Room room) {
        return new LocationRequestResource(room.getEmailAddress());
    }

    private LocationRequestResource(String locationEmailAddress) {
        this.locationEmailAddress = locationEmailAddress;
    }

    @JsonGetter("ResolveAvailability")
    public boolean isResolveAvailability() {
        return RESOLVE_AVAILABILITY;
    }

    @JsonGetter("LocationEmailAddress")
    public String getLocationEmailAddress() {
        return locationEmailAddress;
    }

    @Override
    public String toString() {
        return "LocationRequestResource{" +
                "locationEmailAddress='" + locationEmailAddress + '\'' +
                '}';
    }
}
