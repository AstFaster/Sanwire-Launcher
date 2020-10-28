package fr.sanwire.launcher.core.ui;

import fr.sanwire.launcher.core.Main;
import fr.sanwire.launcher.core.SanwireLauncher;
import fr.sanwire.launcher.core.ui.panels.IPanel;
import fr.sanwire.launcher.core.ui.panels.includes.TopPanel;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {

    private IPanel currentPanel;

    private final SanwireLauncher launcher;
    private final Stage stage;
    private final IPanel topPanel;
    private final GridPane centerPanel;

    public PanelManager(SanwireLauncher launcher, Stage stage) {
        this.launcher = launcher;
        this.stage = stage;
        this.topPanel = new TopPanel();
        this.centerPanel = new GridPane();
    }

    public void init(){
        this.stage.getIcons().add(new Image(Main.class.getResource("/resources/img/logo.png").toExternalForm()));
        this.stage.setTitle(References.LAUNCHER_NAME);
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.show();
        this.stage.centerOnScreen();

        final GridPane layout = new GridPane();
        layout.setStyle("-fx-background-image: url('/resources/img/background.png'); -fx-background-position: center center; -fx-background-size: cover");
        this.stage.setScene(new Scene(layout));

        final RowConstraints topPanelConstraints = new RowConstraints();
        topPanelConstraints.setValignment(VPos.TOP);
        topPanelConstraints.setMaxHeight(25);
        topPanelConstraints.setMinHeight(25);

        layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        layout.add(this.topPanel.getLayout(), 0, 0);

        this.topPanel.init(this);
        layout.add(this.centerPanel, 0, 1);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
    }

    public void showPanel(IPanel panel){
        this.currentPanel = panel;
        this.launcher.getLogger().log(panel.getName() + " is currently open.", LogType.INFO);
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
    }

    public SanwireLauncher getLauncher() {
        return launcher;
    }

    public Stage getStage() {
        return stage;
    }

    public IPanel getCurrentPanel() {
        return currentPanel;
    }
}
