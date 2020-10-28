package fr.sanwire.launcher.api.auth.request;

public class RefreshRequest {

    private String accessToken;
    private String clientToken;

    public RefreshRequest(String accessToken, String clientToken) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
