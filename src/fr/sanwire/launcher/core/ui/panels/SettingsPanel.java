package fr.sanwire.launcher.core.ui.panels;

import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.panels.includes.SelectorPanel;
import javafx.scene.layout.GridPane;

public class SettingsPanel extends AbstractPanel {

    private final GridPane settingsPanel = new GridPane();

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        addHomePanel();

        this.layout.getChildren().addAll(this.settingsPanel, this.addSelectorPanel());
    }

    private void addHomePanel(){
        this.settingsPanel.setStyle("-fx-background-color: #123123");

        this.setTakeAllPlace(this.settingsPanel);
        this.setRight(this.settingsPanel);
    }

    private GridPane addSelectorPanel(){
        final SelectorPanel selectorPanel = new SelectorPanel();
        selectorPanel.setStyle("-fx-background-color: #1C1C1C");
        selectorPanel.setMaxWidth(225.0d);
        selectorPanel.setMinWidth(225.0d);
        selectorPanel.build(this.panelManager);

        this.setTakeAllPlace(selectorPanel);

        return selectorPanel;
    }

    @Override
    public String getName() {
        return "Settings Panel";
    }
}
