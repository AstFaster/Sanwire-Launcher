package fr.sanwire.launcher.core.ui;

import fr.sanwire.launcher.api.auth.exception.AuthException;
import fr.sanwire.launcher.core.SanwireLauncher;
import fr.sanwire.launcher.core.auth.AuthManager;
import fr.sanwire.launcher.core.ui.panels.Panels;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class FxApplication extends Application {
    
    @Override
    public void start(Stage stage) {
        final SanwireLauncher launcher = SanwireLauncher.getInstance();
        launcher.setPanelManager(stage);
        launcher.getPanelManager().init();
        if (new File(References.DIR + "/launcher_profiles.json").exists()) {
            final AuthManager manager = new AuthManager(launcher.getLogger());
            try {
                manager.refresh();
                launcher.getLogger().log("Successfully refresh account from Mojang Service", LogType.INFO);
                manager.save();
                launcher.getLogger().log("Saving auth infos in launcher_profiles.json...", LogType.INFO);
                launcher.getPanelManager().showPanel(Panels.HOME_PANEL);
            } catch (AuthException e) {
                launcher.getLogger().log(e.getMessage(), LogType.ERROR);
                launcher.getPanelManager().showPanel(Panels.LOGIN_PANEL);
            }
        }
        else launcher.getPanelManager().showPanel(Panels.LOGIN_PANEL);
    }
}
