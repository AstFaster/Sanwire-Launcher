package fr.sanwire.launcher.core.ui.panels;

import fr.sanwire.launcher.core.Main;
import fr.sanwire.launcher.api.auth.exception.AuthException;
import fr.sanwire.launcher.core.auth.AuthManager;
import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import javafx.animation.FillTransition;
import javafx.event.Event;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.File;

public class LoginPanel extends AbstractPanel {

    private final GridPane loginPanel = new GridPane();

    private TextField emailField;
    private PasswordField passwordField;
    private Rectangle emailFieldUnderline;
    private Rectangle passwordFieldUnderline;
    private Label forgotPassword;
    private Button connectButton;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        this.layout.getChildren().add(this.addLoginPanel());
    }

    private GridPane addLoginPanel() {
        this.loginPanel.setMaxWidth(375.0d);
        this.loginPanel.setMinWidth(375.0d);
        this.loginPanel.setMaxHeight(425.0d);
        this.loginPanel.setMinHeight(425.0d);

        this.setCenterH(this.loginPanel);
        this.setCenterV(this.loginPanel);
        this.setTakeAllPlace(this.loginPanel);

        this.loginPanel.setStyle("-fx-background-color: #1C1C1C; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        this.loginPanel.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) auth();
        });

        this.loginPanel.getChildren().addAll(
                this.pumpkinLogo(), this.connectLabel(),
                this.emailLabel(), this.emailField(), this.emailFieldUnderline(),
                this.passwordLabel(), this.passwordField(), this.passwordFieldUnderline(),
                this.forgotPassword(), this.connectButton()
        );

        return this.loginPanel;
    }

    private ImageView pumpkinLogo(){
        final Image image = new Image(Main.class.getResource("/resources/img/pumpkin.png").toExternalForm());
        final ImageView view = new ImageView(image);
        view.setFitWidth(150.0d);
        view.setFitHeight(150.0d);
        view.setTranslateY(-100.0d);

        this.setTop(view);
        this.setCenterH(view);
        this.setTakeAllPlace(view);

        return view;
    }

    private Label connectLabel(){
        final Label connectLabel = new Label("Connectez-vous");
        connectLabel.setTranslateY(70.0d);

        connectLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24.0d));
        connectLabel.setStyle("-fx-text-fill: #fff");

        this.setCenterH(connectLabel);
        this.setTop(connectLabel);
        this.setTakeAllPlace(connectLabel);

        return connectLabel;
    }

    private Label emailLabel(){
        final Label emailLabel = new Label("E-mail");
        emailLabel.setTranslateX(40.0d);
        emailLabel.setTranslateY(-58.0d);

        emailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16.0d));
        emailLabel.setStyle("-fx-text-fill: #424242");

        this.setCenterV(emailLabel);
        this.setLeft(emailLabel);
        this.setTakeAllPlace(emailLabel);

        return emailLabel;
    }

    private TextField emailField(){
        this.emailField = new TextField();
        this.emailField.setTranslateX(30.0d);
        this.emailField.setTranslateY(-30.0d);
        this.emailField.setMaxWidth(305.0d);
        this.emailField.setMinWidth(305.0d);
        this.emailField.setMaxHeight(30.0d);
        this.emailField.setMinHeight(30.0d);

        this.emailField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15.0d));
        this.emailField.setStyle("-fx-background-color: none; -fx-text-fill: #ececec");

        this.emailField.setFocusTraversable(false);

        this.emailField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            FillTransition ft;
            if (newVal){
                ft = new FillTransition(Duration.millis(300), this.emailFieldUnderline, Color.web("#424242"), Color.web("#BF581C"));
            } else {
                ft = new FillTransition(Duration.millis(300), this.emailFieldUnderline, Color.web("#BF581C"), Color.web("#424242"));
            }
            ft.play();
        });

        this.emailField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.TAB)){
                this.emailField.setFocusTraversable(true);
                this.passwordField.setFocusTraversable(true);
                this.forgotPassword.setFocusTraversable(true);
            }
        });

        this.setCenterV(this.emailField);
        this.setLeft(this.emailField);
        this.setTakeAllPlace(this.emailField);

        this.emailField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        return this.emailField;
    }

    private Rectangle emailFieldUnderline(){
        this.emailFieldUnderline = new Rectangle();
        this.emailFieldUnderline.setHeight(1.5d);
        this.emailFieldUnderline.setWidth(305.0d);
        this.emailFieldUnderline.setTranslateX(38.0d);
        this.emailFieldUnderline.setTranslateY(-8.0d);

        this.emailFieldUnderline.setStyle("-fx-fill: #424242");

        this.setCenterV(this.emailFieldUnderline);
        this.setLeft(this.emailFieldUnderline);
        this.setTakeAllPlace(this.emailFieldUnderline);

        return this.emailFieldUnderline;
    }

    private Label passwordLabel(){
        final Label passwordLabel = new Label("Mot de passe");
        passwordLabel.setTranslateX(40.0d);
        passwordLabel.setTranslateY(42.0d);

        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16.0d));
        passwordLabel.setStyle("-fx-text-fill: #424242");

        this.setCenterV(passwordLabel);
        this.setLeft(passwordLabel);
        this.setTakeAllPlace(passwordLabel);

        return passwordLabel;
    }

    private PasswordField passwordField(){
        this.passwordField = new PasswordField();
        this.passwordField.setTranslateX(30.0d);
        this.passwordField.setTranslateY(70.0d);
        this.passwordField.setMaxWidth(305.0d);
        this.passwordField.setMinWidth(305.0d);
        this.passwordField.setMaxHeight(30.0d);
        this.passwordField.setMinHeight(30.0d);

        this.passwordField.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15.0d));
        this.passwordField.setStyle("-fx-background-color: none; -fx-text-fill: #ececec");

        this.passwordField.setFocusTraversable(false);

        this.passwordField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            FillTransition ft;
            if (newVal){
                ft = new FillTransition(Duration.millis(300), this.passwordFieldUnderline, Color.web("#424242"), Color.web("#BF581C"));
            } else {
                ft = new FillTransition(Duration.millis(300), this.passwordFieldUnderline, Color.web("#BF581C"), Color.web("#424242"));
            }
            ft.play();
        });

        this.passwordField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.TAB)){
                this.emailField.setFocusTraversable(true);
                this.passwordField.setFocusTraversable(true);
                this.forgotPassword.setFocusTraversable(true);
            }
        });

        this.setCenterV(this.passwordField);
        this.setLeft(this.passwordField);
        this.setTakeAllPlace(this.passwordField);

        this.passwordField.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        return this.passwordField;
    }

    private Rectangle passwordFieldUnderline(){
        this.passwordFieldUnderline = new Rectangle();
        this.passwordFieldUnderline.setHeight(1.5d);
        this.passwordFieldUnderline.setWidth(305.0d);
        this.passwordFieldUnderline.setTranslateX(38.0d);
        this.passwordFieldUnderline.setTranslateY(92.0d);

        this.passwordFieldUnderline.setStyle("-fx-fill: #424242");

        this.setCenterV(this.passwordFieldUnderline);
        this.setLeft(this.passwordFieldUnderline);
        this.setTakeAllPlace(this.passwordFieldUnderline);

        return this.passwordFieldUnderline;
    }

    private Label forgotPassword(){
        this.forgotPassword = new Label("Mot de passe oublié ?");
        this.forgotPassword.setTranslateX(39.0d);
        this.forgotPassword.setTranslateY(110.0d);
        this.forgotPassword.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 12.0d));
        this.forgotPassword.setStyle("-fx-text-fill: #BF581C;");

        forgotPassword.focusedProperty().addListener((obs, oldVal, newVal) -> this.forgotPassword.setUnderline(newVal));

        this.forgotPassword.setOnMouseEntered(e -> {
            this.forgotPassword.setUnderline(true);
            this.forgotPassword.setCursor(Cursor.HAND);
        });
        this.forgotPassword.setOnMouseExited(e -> {
            this.forgotPassword.setUnderline(false);
            this.forgotPassword.setCursor(Cursor.DEFAULT);
        });
        this.forgotPassword.setOnMouseClicked(e -> openUrl("https://www.minecraft.net/fr-fr/password/forgot"));

        setLeft(forgotPassword);
        setCenterV(forgotPassword);

        return this.forgotPassword;
    }

    private Button connectButton(){
        this.connectButton = new Button("Connexion");
        this.connectButton.setMaxWidth(150.0d);
        this.connectButton.setMinWidth(150.0d);
        this.connectButton.setMaxHeight(50.0d);
        this.connectButton.setMaxHeight(50.0d);
        this.connectButton.setTranslateY(-20.0d);

        this.connectButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18.0d));
        this.connectButton.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #B7551D, #DB6520); -fx-background-radius: none; -fx-text-fill: #fff; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        this.connectButton.setFocusTraversable(false);

        this.connectButton.setOnMouseEntered(e -> this.connectButton.setCursor(Cursor.HAND));
        this.connectButton.setOnMouseExited(e -> this.connectButton.setCursor(Cursor.DEFAULT));
        this.connectButton.setOnMouseClicked(e -> auth());

        this.setBottom(this.connectButton);
        this.setCenterH(this.connectButton);

        return this.connectButton;
    }

    private void auth(){
        AuthManager manager = new AuthManager(this.logger);
        try {
            manager.auth(this.emailField.getText(), this.passwordField.getText(), null);
            this.logger.log("Successfully authenticate to Mojang Service.", LogType.INFO);
            manager.printMessageWithInfos();
            manager.save();
            this.logger.log("Saving auth infos in launcher_profiles.json...", LogType.INFO);
            this.panelManager.getCurrentPanel().getLayout().getChildren().clear();
            this.panelManager.showPanel(Panels.HOME_PANEL);
        } catch (AuthException e){
            this.logger.log(e.getMessage(), LogType.ERROR);
            manager.error(this.layout, this.loginPanel, this.connectButton);
            this.emailField.setDisable(false);
            this.passwordField.setDisable(false);
        }
    }

    @Override
    public String getName() {
        return "Login Panel";
    }
}
