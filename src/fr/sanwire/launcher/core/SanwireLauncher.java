package fr.sanwire.launcher.core;

import fr.sanwire.launcher.core.ui.FxApplication;
import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class SanwireLauncher {

    private static SanwireLauncher instance;
    private Logger logger;
    private PanelManager panelManager;

    public SanwireLauncher() {
        instance = this;
        this.logger = new Logger("[" + References.NAME + "]", References.DIR + "/logs/latest-log.log");
    }

    public void start(){
        this.logger.log("Starting Sanwire Launcher...", LogType.INFO);
        try {
            Class.forName("javafx.application.Application");
        } catch (ClassNotFoundException e){
            this.logger.log("Couldn't find JavaFX - Not Found", LogType.ERROR);
            this.shutdown();
        }
        if (!References.DIR.exists()) {
            References.DIR.mkdir();
            this.logger.log("Sanwire directory doesn't exist, so it is created.", LogType.WARN);
        }
        Application.launch(FxApplication.class);
    }

    public void shutdown(){
        this.logger.log("Stopping Sanwire Launcher...", LogType.INFO);
        System.exit(0);
    }

    public Logger getLogger() {
        return logger;
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void setPanelManager(Stage stage) {
        this.panelManager = new PanelManager(this, stage);
    }

    public static SanwireLauncher getInstance() {
        return instance;
    }
}
