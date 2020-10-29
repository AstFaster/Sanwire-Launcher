package fr.sanwire.launcher.core.ui.panels;
;
import fr.sanwire.launcher.api.utils.Saver;
import fr.sanwire.launcher.core.Main;
import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.panels.includes.SelectorPanel;
import fr.sanwire.launcher.core.ui.panels.includes.VersionSelector;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.version.VersionList;
import fr.sanwire.launcher.core.version.VersionManager;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.File;

public class HomePanel extends AbstractPanel {

    private final GridPane homePanel = new GridPane();
    private final SelectorPanel selectorPanel = new SelectorPanel();

    private Button playButton;
    private Button chooseVersion;
    private Label currentVersion;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        this.addHomePanel();

        this.layout.getChildren().addAll(this.homePanel, this.addSelectorPanel());
    }

    private GridPane addSelectorPanel(){
        this.selectorPanel.setStyle("-fx-background-color: #1C1C1C");
        this.selectorPanel.setMaxWidth(225.0d);
        this.selectorPanel.setMinWidth(225.0d);
        this.selectorPanel.build(this.panelManager);

        this.setTakeAllPlace(this.selectorPanel);

        return this.selectorPanel;
    }

    private void addHomePanel(){
        this.setTakeAllPlace(this.homePanel);
        this.setRight(this.homePanel);

        this.homePanel.getChildren().addAll(this.addCenterPanel(), this.addPlayPanel());
    }

    private GridPane addCenterPanel(){
        final GridPane centerPanel = new GridPane();

        this.setTakeAllPlace(centerPanel);

        return centerPanel;
    }

    private GridPane addPlayPanel(){
        final GridPane playPanel = new GridPane();
        playPanel.setMaxHeight(100.0d);
        playPanel.setMinHeight(100.0d);

        playPanel.setStyle("-fx-background-color: rgba(28, 28, 28, 0.6);");

        this.setTakeAllPlace(playPanel);
        this.setBottom(playPanel);

        playPanel.getChildren().addAll(this.playButton(), this.chooseVersion(), this.currentVersion(), this.cornerWeb());

        return playPanel;
    }

    private Button playButton(){
        this.playButton = new Button("Jouer");
        this.playButton.setMaxWidth(300.0d);
        this.playButton.setMinWidth(300.0d);
        this.playButton.setMaxHeight(55.0d);
        this.playButton.setMaxHeight(55.0d);
        this.playButton.setTranslateX(250.0d);

        this.playButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25.0d));
        this.playButton.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");

        this.playButton.setFocusTraversable(false);

        this.playButton.setOnMouseEntered(e -> this.playButton.setCursor(Cursor.HAND));
        this.playButton.setOnMouseExited(e -> this.playButton.setCursor(Cursor.DEFAULT));

        this.setTakeAllPlace(this.playButton);
        this.setCenterV(this.playButton);
        this.setLeft(this.playButton);

        return this.playButton;
    }

    private Button chooseVersion(){
        this.chooseVersion = new Button("Manage Version");
        this.chooseVersion.setMaxWidth(200.0d);
        this.chooseVersion.setMinWidth(200.0d);
        this.chooseVersion.setMaxHeight(55.0d);
        this.chooseVersion.setMaxHeight(55.0d);
        this.chooseVersion.setTranslateX(600.0d);

        this.chooseVersion.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18.0d));
        this.chooseVersion.setStyle("-fx-background-color: rgba(28, 28, 28, 0.4); -fx-text-fill: #fff;");

        this.chooseVersion.setFocusTraversable(false);

        this.chooseVersion.setOnMouseEntered(e -> this.chooseVersion.setCursor(Cursor.HAND));
        this.chooseVersion.setOnMouseExited(e -> this.chooseVersion.setCursor(Cursor.DEFAULT));
        this.chooseVersion.setOnMouseClicked(e -> {
            final VersionSelector versionSelector = new VersionSelector();
            if (new File(References.DIR + "/launcher_options.json").exists())
                versionSelector.build(this.logger, Saver.getInfosFromJsonFile(References.DIR + "/launcher_options.json", "version"), this.currentVersion);
            else versionSelector.build(this.logger, VersionList.FACTION.getName(), this.currentVersion);

            this.logger.log("Opening version selector frame.", LogType.INFO);

            final GaussianBlur blur = new GaussianBlur(5.0d);
            Thread t = new Thread(() -> {
                while (versionSelector.isVisible()) {
                    this.homePanel.setEffect(blur);
                    this.selectorPanel.setEffect(blur);
                    this.selectorPanel.setDisable(true);
                    this.homePanel.setDisable(true);
                }
                if (!versionSelector.isVisible()){
                    this.homePanel.setEffect(null);
                    this.selectorPanel.setEffect(null);
                    this.selectorPanel.setDisable(false);
                    this.homePanel.setDisable(false);
                    this.logger.log("Closing version selector frame.", LogType.INFO);
                }
            });
            t.start();

            this.layout.getChildren().add(versionSelector);
        });

        this.setTakeAllPlace(this.chooseVersion);
        this.setCenterV(this.chooseVersion);
        this.setLeft(this.chooseVersion);

        return this.chooseVersion;
    }

    private Label currentVersion(){
        this.currentVersion = new Label("Current Version : ");
        if (new File(References.DIR + "/launcher_options.json").exists()) {
            this.currentVersion.setText(this.currentVersion.getText() + Saver.getInfosFromJsonFile(References.DIR + "/launcher_options.json", "version"));
        }
        else {
            this.currentVersion.setText(this.currentVersion.getText() + VersionList.FACTION.getName());
            final VersionManager manager = new VersionManager();
            manager.save(VersionList.FACTION, this.logger);
        }
        this.logger.log("Changing current version to " + Saver.getInfosFromJsonFile(References.DIR + "/launcher_options.json", "version"), LogType.INFO);

        this.currentVersion.setTranslateX(850.0d);

        this.currentVersion.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18.0d));
        this.currentVersion.setStyle("-fx-text-fill: #ececec;");

        this.setTakeAllPlace(this.currentVersion);
        this.setCenterV(this.currentVersion);
        this.setLeft(this.currentVersion);

        return this.currentVersion;
    }
    
    private ImageView cornerWeb(){
        final ImageView view = new ImageView(new Image(Main.class.getResource("/resources/img/cobweb.png").toExternalForm()));
        view.setFitWidth(100.0d);
        view.setFitHeight(100.0d);
        view.setRotate(90.0d);

        this.setTakeAllPlace(view);
        this.setRight(view);
        this.setBottom(view);

        return view;
    }

    @Override
    public String getName() {
        return "Home Panel";
    }
}
