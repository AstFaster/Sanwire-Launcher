package fr.sanwire.launcher.core.ui.panels.includes;

import fr.sanwire.launcher.core.auth.AuthManager;
import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.panels.Panels;
import fr.sanwire.launcher.core.utils.References;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SelectorPanel extends GridPane {

    private PanelManager panelManager;

    private Label home;
    private Label news;
    private Label store;
    private Label settings;

    public void build(PanelManager panelManager){
        this.panelManager = panelManager;

        this.setGrowAndAlignment(this, null, HPos.LEFT);

        this.getChildren().addAll(addPlayerPanel(), home(), news(), store(), settings(), rectangle());
    }

    private Label home(){
        this.home = new Label("Accueil");
        this.home.setTranslateX(20.0d);
        this.home.setTranslateY(110.0d);;

        this.activePanelOptions(this.home);
        this.labelHover(this.home);
        this.labelClicked(this.home);

        this.setGrowAndAlignment(this.home, VPos.TOP, HPos.LEFT);

        return this.home;
    }

    private Label news(){
        this.news = new Label("Nouveautés");
        this.news.setTranslateX(20.0d);
        this.news.setTranslateY(155.0d);

        this.news.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19.0d));
        this.news.setStyle("-fx-text-fill: #ececec");

        this.labelHover(this.news);
        this.labelClicked(this.news);

        this.setGrowAndAlignment(this.news, VPos.TOP, HPos.LEFT);

        return this.news;
    }

    private Label store(){
        this.store = new Label("Boutique");
        this.store.setTranslateX(20.0d);
        this.store.setTranslateY(200.0d);

        this.store.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19.0d));
        this.store.setStyle("-fx-text-fill: #ececec");

        this.labelHover(this.store);
        this.labelClicked(this.store);

        this.setGrowAndAlignment(this.store, VPos.TOP, HPos.LEFT);

        return this.store;
    }

    private Label settings(){
        this.settings = new Label("Paramètres");
        this.settings.setTranslateX(20.0d);
        this.settings.setTranslateY(-20.0d);

        this.activePanelOptions(this.settings);
        this.labelHover(this.settings);
        this.labelClicked(this.settings);

        this.setGrowAndAlignment(this.settings, VPos.BOTTOM, HPos.LEFT);

        return this.settings;
    }

    private Rectangle rectangle(){
        final Rectangle rectangle = new Rectangle();
        rectangle.setHeight(15.0d);
        rectangle.setWidth(5.0d);

        if (this.panelManager.getCurrentPanel().equals(Panels.HOME_PANEL)) {
            rectangle.setTranslateY(113.0d);
            this.setGrowAndAlignment(rectangle, VPos.TOP, HPos.LEFT);
        }
        else {
            rectangle.setTranslateY(-25.0d);
            this.setGrowAndAlignment(rectangle, VPos.BOTTOM, HPos.LEFT);
        }


        rectangle.setFill(Color.web("#BF581C"));

        return rectangle;
    }

    private GridPane addPlayerPanel(){
        final GridPane playerPanel = new GridPane();
        playerPanel.setMaxHeight(100.0d);
        playerPanel.setMinHeight(100.0d);

        this.setGrowAndAlignment(playerPanel, VPos.TOP, null);

        playerPanel.getChildren().addAll(playerHead(), playerName(), playerEmail(), disconnectButton(), playerPanelSeparator());

        return playerPanel;
    }

    private WebView playerHead(){
        final WebView view = new WebView();
        WebEngine engine = view.getEngine();

        engine.load("https://crafatar.com/avatars/" + AuthManager.getUuid() + "?size=32&overlay");

        view.setMaxWidth(32.0d);
        view.setMinWidth(32.0d);
        view.setMaxHeight(32.0d);
        view.setMinHeight(32.0d);
        view.setTranslateX(15.0d);
        view.setTranslateY(15.0d);

        this.setGrowAndAlignment(view, VPos.TOP, HPos.LEFT);

        return view;
    }

    private Label playerName(){
        final Label playerName = new Label(AuthManager.getUsername());
        playerName.setTranslateX(55.0d);
        playerName.setTranslateY(8.0d);

        playerName.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16.0d));
        playerName.setStyle("-fx-text-fill: #fff");

        this.setGrowAndAlignment(playerName, VPos.TOP, HPos.LEFT);

        return playerName;
    }

    private Label playerEmail(){
        final Label playerEmail = new Label(emailSize());
        playerEmail.setTranslateX(55.0d);
        playerEmail.setTranslateY(25.0d);

        playerEmail.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 12.0d));
        playerEmail.setStyle("-fx-text-fill: #BF581C");

        this.setGrowAndAlignment(playerEmail, VPos.TOP, HPos.LEFT);

        return playerEmail;
    }

    private Button disconnectButton(){
        final Button disconnectButton = new Button("Déconnexion");
        disconnectButton.setMaxWidth(110.0d);
        disconnectButton.setMinWidth(110.0d);
        disconnectButton.setMaxHeight(25.0d);
        disconnectButton.setMinHeight(25.0d);
        disconnectButton.setTranslateX(13.0d);
        disconnectButton.setTranslateY(55.0d);

        disconnectButton.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14.0d));
        disconnectButton.setStyle("-fx-background-color: #282828; -fx-text-fill: #FF3838; -fx-border-radius: 3px; -fx-border-color: #FF3838");

        disconnectButton.setFocusTraversable(false);

        disconnectButton.setOnMouseEntered(e -> disconnectButton.setCursor(Cursor.HAND));
        disconnectButton.setOnMouseExited(e -> disconnectButton.setCursor(Cursor.DEFAULT));
        disconnectButton.setOnMouseClicked(e ->{
            this.panelManager.getCurrentPanel().getLayout().getChildren().clear();
            if (new File(References.DIR + "/launcher_profiles.json").exists()) new File(References.DIR + "/launcher_profiles.json").delete();
            this.panelManager.showPanel(Panels.LOGIN_PANEL);
        });

        this.setGrowAndAlignment(disconnectButton, VPos.TOP, HPos.LEFT);

        return disconnectButton;
    }

    private Rectangle playerPanelSeparator(){
        final Rectangle playerPanelSeparator = new Rectangle();
        playerPanelSeparator.setWidth(225.0d);
        playerPanelSeparator.setHeight(1.0d);
        playerPanelSeparator.setTranslateY(90.0d);

        playerPanelSeparator.setFill(Color.web("#424242"));

        this.setGrowAndAlignment(playerPanelSeparator, VPos.TOP, HPos.LEFT);

        return playerPanelSeparator;
    }

    private String emailSize(){
        if (AuthManager.getEmail().length() > 18){
            return AuthManager.getEmail().substring(0, 18) + "...";
        }
        return AuthManager.getEmail();
    }

    private void labelHover(Label label){

        label.setOnMouseEntered(e -> {
            label.setStyle("-fx-text-fill: #fff");
            label.setCursor(Cursor.HAND);
        });
        label.setOnMouseExited(e -> {
            if (label.equals(this.home) && this.panelManager.getCurrentPanel().equals(Panels.HOME_PANEL)) return;
            if (label.equals(this.settings) && this.panelManager.getCurrentPanel().equals(Panels.SETTINGS_PANEL)) return;

            label.setStyle("-fx-text-fill: #ececec");
            label.setCursor(Cursor.DEFAULT);
        });
    }

    private void labelClicked(Label label){
        label.setOnMouseClicked(e -> {
            if (label.equals(this.home) && !this.panelManager.getCurrentPanel().equals(Panels.HOME_PANEL)){
                this.panelManager.getCurrentPanel().getLayout().getChildren().clear();
                this.panelManager.showPanel(Panels.HOME_PANEL);
            }
            else if (label.equals(this.settings) && !this.panelManager.getCurrentPanel().equals(Panels.SETTINGS_PANEL)){
                this.panelManager.getCurrentPanel().getLayout().getChildren().clear();
                this.panelManager.showPanel(Panels.SETTINGS_PANEL);
            }
            else if (label.equals(this.news)){
                openUrl("https://hypixel.net/forums");
            }
            else if (label.equals(this.store)){
                openUrl("https://store.hypixel.net");
            }
        });

    }

    private void activePanelOptions(Label label){
        if (label.equals(this.home) && this.panelManager.getCurrentPanel().equals(Panels.HOME_PANEL)){
            label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 19.0d));
            label.setStyle("-fx-text-fill: #fff");
        } else if (label.equals(this.settings) && this.panelManager.getCurrentPanel().equals(Panels.SETTINGS_PANEL)){
            label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 19.0d));
            label.setStyle("-fx-text-fill: #fff");
        } else {
            label.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19.0d));
            label.setStyle("-fx-text-fill: #ececec");
        }
    }

    private void setGrowAndAlignment(Node node, VPos vPos, HPos hPos){
        GridPane.setVgrow(node, Priority.ALWAYS);
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setValignment(node, vPos);
        GridPane.setHalignment(node, hPos);
    }

    private void openUrl(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
