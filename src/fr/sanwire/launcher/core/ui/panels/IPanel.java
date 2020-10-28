package fr.sanwire.launcher.core.ui.panels;

import fr.sanwire.launcher.core.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager panelManager);
    GridPane getLayout();
    String getName();

}
