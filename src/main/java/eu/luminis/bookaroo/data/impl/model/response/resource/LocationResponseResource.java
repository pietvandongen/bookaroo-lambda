package eu.luminis.bookaroo.data.impl.model.response.resource;

import com.fasterxml.jackson.annotation.JsonSetter;

public class LocationResponseResource {

    private String locationEmailAddress;

    public String getLocationEmailAddress() {
        return locationEmailAddress;
    }

    @JsonSetter("LocationEmailAddress")
    public void setLocationEmailAddress(String locationEmailAddress) {
        this.locationEmailAddress = locationEmailAddress;
    }

    @Override
    public String toString() {
        return "LocationResponseResource{" +
                "locationEmailAddress='" + locationEmailAddress + '\'' +
                '}';
    }
}
