package eu.luminis.bookaroo.data.impl.model.request.resource;

import com.fasterxml.jackson.annotation.JsonGetter;
import eu.luminis.bookaroo.model.Room;
import java.util.Collections;
import java.util.List;

public class LocationConstraintRequestResource {

    private static final boolean IS_REQUIRED = true;
    private static final boolean SUGGEST_LOCATION = false;

    private final List<LocationRequestResource> locations;

    public static LocationConstraintRequestResource of(Room room) {
        return new LocationConstraintRequestResource(Collections.singletonList(LocationRequestResource.of(room)));
    }

    private LocationConstraintRequestResource(List<LocationRequestResource> locations) {
        this.locations = locations;
    }

    @JsonGetter("IsRequired")
    public boolean isRequired() {
        return IS_REQUIRED;
    }

    @JsonGetter("SuggestLocation")
    public boolean isSuggestLocation() {
        return SUGGEST_LOCATION;
    }

    @JsonGetter("Locations")
    public List<LocationRequestResource> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return "LocationConstraintRequestResource{" +
                "locations=" + locations +
                '}';
    }
}
