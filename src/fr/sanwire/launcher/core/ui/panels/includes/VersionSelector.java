package fr.sanwire.launcher.core.ui.panels.includes;

import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;
import fr.sanwire.launcher.core.version.VersionList;
import fr.sanwire.launcher.core.version.VersionManager;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class VersionSelector extends GridPane {

    private Label header;
    private Rectangle separator;
    private Label content;
    private ToggleButton faction;
    private ToggleButton skyblock;
    private ToggleButton aventure;

    private Logger logger;
    private Label versionLabel;

    private final ToggleGroup group = new ToggleGroup();

    public void build(Logger logger, String baseVersion, Label versionLabel){
        this.logger = logger;
        this.versionLabel = versionLabel;

        this.setVisible(true);
        this.setMaxWidth(500.0d);
        this.setMinWidth(500.0d);
        this.setMaxHeight(350.0d);
        this.setMinHeight(350.0d);

        this.setStyle("-fx-background-color: #1C1C1C; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        this.setGrowAndAlignment(this, VPos.CENTER, HPos.CENTER);

        this.getChildren().addAll(this.header(), this.separator(), this.content(), this.faction(), this.skyblock(), this.aventure(), this.validateButton());

        this.version(baseVersion);
    }

    private void version(String baseVersion){
        switch (baseVersion.toLowerCase()){
            case "faction":
                this.faction.setSelected(true);
                this.faction.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-border-color: #fff; -fx-text-fill: #fff;");
                break;
            case "skyblock":
                this.skyblock.setSelected(true);
                this.skyblock.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-border-color: #fff; -fx-text-fill: #fff;");
                break;
            case "aventure":
                this.aventure.setSelected(true);
                this.aventure.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-border-color: #fff; -fx-text-fill: #fff;");
                break;
        }
    }

    private Label header(){
        this.header = new Label("Version");
        this.header.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 22.0d));
        this.header.setStyle("-fx-text-fill: #fff");
        this.header.setTranslateY(25.0d);

        this.setGrowAndAlignment(this.header, VPos.TOP, HPos.CENTER);

        return this.header;
    }

    private Rectangle separator(){
        this.separator = new Rectangle();
        this.separator.setHeight(1.0d);
        this.separator.setWidth(460.0d);
        this.separator.setTranslateY(70.0d);
        this.separator.setFill(Color.web("#424242"));

        this.setGrowAndAlignment(this.separator, VPos.TOP, HPos.CENTER);

        return this.separator;
    }

    private Label content(){
        this.content = new Label("Choisissez votre version de lancement.");
        this.content.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16.0d));
        this.content.setStyle("-fx-text-fill: #d4d0d0");
        this.content.setTranslateY(90.0d);

        this.setGrowAndAlignment(this.content, VPos.TOP, HPos.CENTER);

        return this.content;
    }

    private ToggleButton faction(){
        this.faction = new ToggleButton("Faction");
        this.faction.setToggleGroup(this.group);
        this.faction.setTranslateY(-20.0d);
        this.faction.setMaxWidth(150.0d);
        this.faction.setMinWidth(150.0d);
        this.faction.setMaxHeight(50.0d);
        this.faction.setMinHeight(50.0d);

        this.faction.setFocusTraversable(false);

        this.faction.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20.0d));
        this.faction.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");

        this.faction.setOnMouseEntered(e -> this.faction.setCursor(Cursor.HAND));
        this.faction.setOnMouseExited(e -> this.faction.setCursor(Cursor.DEFAULT));
        this.faction.setOnMouseClicked(e -> {
            this.faction.setSelected(true);
            this.faction.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-border-color: #fff; -fx-text-fill: #fff;");
            this.skyblock.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");
            this.skyblock.setSelected(false);
            this.aventure.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");
            this.aventure.setSelected(false);
        });

        this.setGrowAndAlignment(this.faction, VPos.CENTER, HPos.CENTER);

        return this.faction;
    }

    private ToggleButton skyblock(){
        this.skyblock = new ToggleButton("SkyBlock");
        this.skyblock.setToggleGroup(this.group);
        this.skyblock.setTranslateY(40.0d);
        this.skyblock.setMaxWidth(150.0d);
        this.skyblock.setMinWidth(150.0d);
        this.skyblock.setMaxHeight(50.0d);
        this.skyblock.setMinHeight(50.0d);

        this.skyblock.setFocusTraversable(false);

        this.skyblock.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20.0d));
        this.skyblock.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");

        this.skyblock.setOnMouseEntered(e -> this.skyblock.setCursor(Cursor.HAND));
        this.skyblock.setOnMouseExited(e -> this.skyblock.setCursor(Cursor.DEFAULT));
        this.skyblock.setOnMouseClicked(e -> {
            this.skyblock.setSelected(true);
            this.skyblock.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-border-color: #fff; -fx-text-fill: #fff;");
            this.aventure.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");
            this.aventure.setSelected(false);
            this.faction.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");
            this.faction.setSelected(false);
        });

        this.setGrowAndAlignment(this.skyblock, VPos.CENTER, HPos.CENTER);

        return this.skyblock;
    }

    private ToggleButton aventure(){
        this.aventure = new ToggleButton("Aventure");
        this.aventure.setToggleGroup(this.group);
        this.aventure.setTranslateY(100.0d);
        this.aventure.setMaxWidth(150.0d);
        this.aventure.setMinWidth(150.0d);
        this.aventure.setMaxHeight(50.0d);
        this.aventure.setMinHeight(50.0d);

        this.skyblock.setFocusTraversable(false);

        this.aventure.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 20.0d));
        this.aventure.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");

        this.aventure.setOnMouseEntered(e -> this.aventure.setCursor(Cursor.HAND));
        this.aventure.setOnMouseExited(e -> this.aventure.setCursor(Cursor.DEFAULT));
        this.aventure.setOnMouseClicked(e -> {
            this.aventure.setSelected(true);
            this.aventure.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-border-color: #fff; -fx-text-fill: #fff;");
            this.skyblock.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");
            this.skyblock.setSelected(false);
            this.faction.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff;");
            this.faction.setSelected(false);
        });

        this.setGrowAndAlignment(this.aventure, VPos.CENTER, HPos.CENTER);

        return this.aventure;
    }

    private Button validateButton(){
        final Button validateButton = new Button("Ok");
        validateButton.setTranslateY(-10.0d);
        validateButton.setMaxWidth(50.0d);
        validateButton.setMinWidth(50.0d);
        validateButton.setMaxHeight(35.0d);
        validateButton.setMinHeight(35.0d);

        validateButton.setFocusTraversable(false);

        validateButton.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14.0d));
        validateButton.setStyle("-fx-background-color: #1C1C1C; -fx-text-fill: #BF581C; -fx-border-radius: 3px; -fx-border-color: #BF581C");

        validateButton.setOnMouseEntered(e -> validateButton.setCursor(Cursor.HAND));
        validateButton.setOnMouseExited(e -> validateButton.setCursor(Cursor.DEFAULT));
        validateButton.setOnMouseClicked(e -> {
            this.setVisible(false);
            final VersionManager manager = new VersionManager();
            if (this.group.getSelectedToggle().equals(this.faction)){
                manager.save(VersionList.FACTION, this.logger);
                this.versionLabel.setText("Current Version : " + VersionList.FACTION.getName());
                this.logger.log("Changing current version to " + VersionList.FACTION.getName(), LogType.INFO);
            } else if (this.group.getSelectedToggle().equals(this.skyblock)){
                manager.save(VersionList.SKYBLOCK, this.logger);
                this.versionLabel.setText("Current Version : " + VersionList.SKYBLOCK.getName());
                this.logger.log("Changing current version to " + VersionList.SKYBLOCK.getName(), LogType.INFO);
            } else if (this.group.getSelectedToggle().equals(this.aventure)){
                manager.save(VersionList.AVENTURE, this.logger);
                this.versionLabel.setText("Current Version : " + VersionList.AVENTURE.getName());
                this.logger.log("Changing current version to " + VersionList.AVENTURE.getName(), LogType.INFO);
            }
        });

        this.setGrowAndAlignment(validateButton, VPos.BOTTOM, HPos.CENTER);

        return validateButton;
    }

    private void setGrowAndAlignment(Node node, VPos vPos, HPos hPos){
        GridPane.setVgrow(node, Priority.ALWAYS);
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setValignment(node, vPos);
        GridPane.setHalignment(node, hPos);
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public ToggleButton getFaction() {
        return faction;
    }

    public ToggleButton getSkyblock() {
        return skyblock;
    }

    public ToggleButton getAventure() {
        return aventure;
    }
}
