package fr.sanwire.launcher.core.ui.panels;

import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.panels.includes.SelectorPanel;
import javafx.scene.layout.GridPane;

public class HomePanel extends AbstractPanel {

    private final GridPane homePanel = new GridPane();
    private final GridPane leftBarPanel = new GridPane();

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        addLeftBarPanel();
        addHomePanel();

        this.layout.getChildren().addAll(this.homePanel, this.leftBarPanel);
    }

    private void addHomePanel(){

        this.setTakeAllPlace(this.homePanel);
        this.setRight(this.homePanel);
    }

    private void addLeftBarPanel(){
        this.leftBarPanel.setStyle("-fx-background-color: #1C1C1C");
        this.leftBarPanel.setMaxWidth(225.0d);
        this.leftBarPanel.setMinWidth(225.0d);

        this.setTakeAllPlace(this.leftBarPanel);
        this.setLeft(this.leftBarPanel);

        this.leftBarPanel.getChildren().addAll(this.addSelectorPanel());
    }

    private GridPane addSelectorPanel(){
        final SelectorPanel selectorPanel = new SelectorPanel();
        selectorPanel.build(this.panelManager);

        this.setTakeAllPlace(selectorPanel);

        return selectorPanel;
    }

    @Override
    public String getName() {
        return "Home Panel";
    }
}
