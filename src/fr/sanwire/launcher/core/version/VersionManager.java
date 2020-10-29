package fr.sanwire.launcher.core.version;

import fr.sanwire.launcher.api.utils.Saver;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;

public class VersionManager {

    public void save(VersionList version, Logger logger){
        final Saver saver = new Saver(References.DIR + "/launcher_options.json");
        saver.put("version", version.getName());
        logger.log("Saving current version in launcher_profiles.json", LogType.INFO);
        saver.save();
    }
}
