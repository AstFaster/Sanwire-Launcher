package fr.sanwire.launcher.api.auth.request;

import fr.sanwire.launcher.api.auth.util.AuthAgent;

public class AuthRequest {

    private AuthAgent agent;
    private String username;
    private String password;
    private String clientToken;

    public AuthRequest(AuthAgent agent, String username, String password, String clientToken) {
        this.agent = agent;
        this.username = username;
        this.password = password;
        this.clientToken = clientToken;
    }

    public AuthAgent getAgent() {
        return agent;
    }

    public void setAgent(AuthAgent agent) {
        this.agent = agent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }
}
