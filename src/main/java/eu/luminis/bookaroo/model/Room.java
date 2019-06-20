package eu.luminis.bookaroo.model;

public enum Room {
    
    SMALL("vergaderruimte-arnhem-klein@luminis.eu"),
    LARGE("vergaderruimte-arnhem-groot@luminis.eu");

    private final String emailAddress;

    Room(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
