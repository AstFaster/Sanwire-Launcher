package fr.sanwire.launcher.api.auth.util;

public class AuthProfile {

    private String name;
    private String id;

    public AuthProfile() {
        this.name = "";
        this.id = "";
    }

    public AuthProfile(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
