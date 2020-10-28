package fr.sanwire.launcher.api.auth.response;

import fr.sanwire.launcher.api.auth.util.AuthProfile;

public class AuthResponse {

    private String accessToken;
    private String clientToken;
    private AuthProfile[] availableProfiles;
    private AuthProfile selectedProfile;

    public AuthResponse(String accessToken, String clientToken, AuthProfile[] availableProfiles, AuthProfile selectedProfile) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.availableProfiles = availableProfiles;
        this.selectedProfile = selectedProfile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public AuthProfile[] getAvailableProfiles() {
        return availableProfiles;
    }

    public AuthProfile getSelectedProfile() {
        return selectedProfile;
    }
}
