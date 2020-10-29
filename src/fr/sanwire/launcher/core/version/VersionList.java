package fr.sanwire.launcher.core.version;

public enum VersionList {

    FACTION("Faction"),
    SKYBLOCK("SkyBlock"),
    AVENTURE("Aventure");

    private String name;

    VersionList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
