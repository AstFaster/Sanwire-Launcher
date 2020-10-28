package fr.sanwire.launcher.core.ui.panels;

import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.panels.includes.SelectorPanel;
import javafx.scene.layout.GridPane;

public class SettingsPanel extends AbstractPanel {

    private final GridPane settingsPanel = new GridPane();
    private final GridPane leftBarPanel = new GridPane();

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        addHomePanel();
        addLeftBarPanel();

        this.layout.getChildren().addAll(this.settingsPanel, this.leftBarPanel);
    }

    private void addHomePanel(){
        this.settingsPanel.setStyle("-fx-background-color: #123123");

        this.setTakeAllPlace(this.settingsPanel);
        this.setRight(this.settingsPanel);
    }

    private void addLeftBarPanel(){
        this.leftBarPanel.setStyle("-fx-background-color: #1C1C1C");
        this.leftBarPanel.setMaxWidth(225.0d);
        this.leftBarPanel.setMinWidth(225.0d);

        this.setTakeAllPlace(this.leftBarPanel);
        this.setLeft(this.leftBarPanel);

        this.leftBarPanel.getChildren().addAll(this.addPlayerPanel(), this.addSelectorPanel());
    }

    private GridPane addPlayerPanel(){
        final GridPane playerPanel = new GridPane();
        playerPanel.setMaxHeight(100.0d);
        playerPanel.setMinHeight(100.0d);

        this.setTakeAllPlace(playerPanel);

        return playerPanel;
    }

    private GridPane addSelectorPanel(){
        final SelectorPanel selectorPanel = new SelectorPanel();
        selectorPanel.build(this.panelManager);

        this.setTakeAllPlace(selectorPanel);

        return selectorPanel;
    }

    @Override
    public String getName() {
        return "Settings Panel";
    }
}
