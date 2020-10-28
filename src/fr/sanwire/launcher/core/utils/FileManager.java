package fr.sanwire.launcher.core.utils;

import java.io.File;

public class FileManager {

    public static File createRoamingGameDir(String dirName){
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) return new File(System.getProperty("user.home") + "\\AppData\\Roaming\\." + dirName);
        else if (os.contains("mac")) return new File(System.getProperty("user.home") + "/Library/Application Support/" + dirName);
        else return new File(System.getProperty("user.home") + "/." + dirName);
    }

}
