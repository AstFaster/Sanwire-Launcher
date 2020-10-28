package fr.sanwire.launcher.api.auth.util;

public class AuthAgent {

    public static final AuthAgent MINECRAFT = new AuthAgent("Minecraft", 1);

    private String name;
    private int version;

    public AuthAgent(String name, int version){
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
