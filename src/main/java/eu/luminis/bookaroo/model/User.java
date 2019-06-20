package eu.luminis.bookaroo.model;

public class User {

    private final String accessToken;

    private User(String accessToken) {
        this.accessToken = accessToken;
    }

    public static User withAccessToken(String accessToken) {
        return new User(accessToken);
    }

    public String getAccessToken() {
        return accessToken;
    }
}
