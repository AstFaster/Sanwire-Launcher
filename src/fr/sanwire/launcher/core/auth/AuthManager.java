package fr.sanwire.launcher.core.auth;

import fr.sanwire.launcher.api.auth.Auth;
import fr.sanwire.launcher.api.auth.exception.AuthException;
import fr.sanwire.launcher.api.auth.request.AuthRequest;
import fr.sanwire.launcher.api.auth.request.RefreshRequest;
import fr.sanwire.launcher.api.auth.response.AuthResponse;
import fr.sanwire.launcher.api.auth.response.RefreshResponse;
import fr.sanwire.launcher.api.auth.util.AuthAgent;
import fr.sanwire.launcher.api.utils.Saver;
import fr.sanwire.launcher.core.ui.panels.includes.Dialog;
import fr.sanwire.launcher.core.utils.References;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import org.apache.commons.validator.routines.EmailValidator;

public class AuthManager {

    private Logger logger;

    private static String email;
    private static String username;
    private static String uuid;
    private static String clientToken;
    private static String accessToken;

    public AuthManager(Logger logger) {
        this.logger = logger;
    }

    public void auth(String email, String password, String clientToken) throws AuthException {
        AuthManager.email = email;

        Auth auth = new Auth(this.logger);
        AuthResponse response = (AuthResponse) auth.sendRequestWithResponse(new AuthRequest(AuthAgent.MINECRAFT, email, password, clientToken));

        AuthManager.username = response.getSelectedProfile().getName();
        AuthManager.uuid = response.getSelectedProfile().getId();
        AuthManager.clientToken = response.getClientToken();
        AuthManager.accessToken = response.getAccessToken();
    }

    public void refresh() throws AuthException {
        AuthManager.email = Saver.getInfosFromJsonFile(References.DIR + "/launcher_profiles.json", "email");
        AuthManager.username = Saver.getInfosFromJsonFile(References.DIR + "/launcher_profiles.json", "username");
        AuthManager.uuid = Saver.getInfosFromJsonFile(References.DIR + "/launcher_profiles.json", "uuid");
        AuthManager.clientToken = Saver.getInfosFromJsonFile(References.DIR + "/launcher_profiles.json", "clientToken");
        AuthManager.accessToken = Saver.getInfosFromJsonFile(References.DIR + "/launcher_profiles.json", "accessToken");

        Auth auth = new Auth(this.logger);
        RefreshResponse response = (RefreshResponse) auth.sendRequestWithResponse(new RefreshRequest(AuthManager.accessToken, AuthManager.clientToken));

        AuthManager.username = response.getSelectedProfile().getName();
        AuthManager.uuid = response.getSelectedProfile().getId();
        AuthManager.clientToken = response.getClientToken();
        AuthManager.accessToken = response.getAccessToken();
    }

    public void save(){
        Saver saver = new Saver(References.DIR + "/launcher_profiles.json");

        saver.put("email", AuthManager.email);
        saver.put("username", AuthManager.username);
        saver.put("uuid", AuthManager.uuid);
        saver.put("clientToken", AuthManager.clientToken);
        saver.put("accessToken", AuthManager.accessToken);

        saver.save();
    }

    public void printMessageWithInfos(){
        this.logger.log("=====================AUTH=====================", LogType.INFO);
        this.logger.log("Email : " + AuthManager.email, LogType.INFO);
        this.logger.log("Username : " + AuthManager.username, LogType.INFO);
        this.logger.log("Uuid : " + AuthManager.uuid, LogType.INFO);
        this.logger.log("Client Token : " + AuthManager.clientToken, LogType.INFO);
        this.logger.log("Access Token : " + AuthManager.accessToken, LogType.INFO);
        this.logger.log("===============================================", LogType.INFO);
    }

    public void error(GridPane layout, GridPane panel, Button connectButton){
        Dialog dialog = new Dialog(null, null, 420.0d, 180.0d);
        GaussianBlur blur = new GaussianBlur(3.0d);
        panel.setEffect(blur);
        if (!isValidEmail(AuthManager.email)) {
            dialog.setHeader("Adresse email non valide !");
            dialog.setContent("Merci de vérifier votre adresse email.");
        } else {
            dialog.setHeader("Echec de l'authentification !");
            dialog.setContent("L'adresse email ou le mot de passe est incorrect.");
        }
        dialog.build();
        layout.getChildren().add(dialog);

        Thread t = new Thread(() -> {
            while (!dialog.isButtonClicked()){
                connectButton.setDisable(true);
            }
            if (dialog.isButtonClicked()){
                connectButton.setDisable(false);
                panel.setEffect(null);
            }
        });
        t.start();
    }

    private boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static String getEmail() {
        return email;
    }
    public static String getUsername() {
        return username;
    }
    public static String getUuid() {
        return uuid;
    }
    public static String getClientToken() {
        return clientToken;
    }
    public static String getAccessToken() {
        return accessToken;
    }
}
