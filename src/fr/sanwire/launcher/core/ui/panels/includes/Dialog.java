package fr.sanwire.launcher.core.ui.panels.includes;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Dialog extends GridPane {

    private String header;
    private String content;
    private double width;
    private double height;

    private boolean buttonClicked = false;

    public Dialog(String header, String content, double width, double height) {
        this.header = header;
        this.content = content;
        this.width = width;
        this.height = height;
    }

    public void build(){

        this.setVisible(true);
        this.setMaxWidth(this.width);
        this.setMinWidth(this.width);
        this.setMaxHeight(this.height);
        this.setMinHeight(this.height);

        this.setStyle("-fx-background-color: #1C1C1C; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

        setGrowAndAlignment(this, VPos.CENTER, HPos.CENTER);

        this.getChildren().addAll(header(), separator(), content(), button());
    }

    private Label header(){
        Label header = new Label(this.header);
        header.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 22.0d));
        header.setStyle("-fx-text-fill: #fff");
        header.setTranslateY(25.0d);

        setGrowAndAlignment(header, VPos.TOP, HPos.CENTER);

        return header;
    }

    private Separator separator(){
        Separator separator = new Separator();
        separator.setMaxHeight(1.0d);
        separator.setMinHeight(1.0d);
        separator.setMaxWidth(this.width - 40.0d);
        separator.setMinWidth(this.width - 40.0d);
        separator.setTranslateY(70.0d);
        separator.setStyle("-fx-background-color: #616161; -fx-opacity: 20%");

        setGrowAndAlignment(separator, VPos.TOP, HPos.CENTER);

        return separator;
    }

    private Label content(){
        Label header = new Label(this.content);
        header.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16.0d));
        header.setStyle("-fx-text-fill: #d4d0d0");
        header.setTranslateY(10.0d);

        setGrowAndAlignment(header, VPos.CENTER, HPos.CENTER);

        return header;
    }

    private Button button(){
        Button button = new Button("Ok");
        button.setMinWidth(55);
        button.setMaxWidth(55);
        button.setMinHeight(35);
        button.setMaxHeight(35);
        button.setTranslateY(-8.0d);

        button.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 14.0d));
        button.setStyle("-fx-background-color: #232323; -fx-border-color: #282828; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-text-fill: #d4d0d0");

        button.setOnMouseEntered(e -> {
            button.setCursor(Cursor.HAND);
            button.setStyle("-fx-background-color: #282828; -fx-border-color: #2D2D2D; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-text-fill: #d4d0d0");
        });
        button.setOnMouseExited(e -> {
            button.setCursor(Cursor.DEFAULT);
            button.setStyle("-fx-background-color: #232323; -fx-border-color: #282828; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-text-fill: #d4d0d0");
        });
        button.setOnAction(e -> {
            this.setVisible(false);
            this.buttonClicked = true;
        });

        setGrowAndAlignment(button, VPos.BOTTOM, HPos.CENTER);

        return button;
    }

    private void setGrowAndAlignment(Node node, VPos vPos, HPos hPos){
        GridPane.setVgrow(node, Priority.ALWAYS);
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setValignment(node, vPos);
        GridPane.setHalignment(node, hPos);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPaneWidth() {
        return width;
    }

    public void setPaneWidth(double width) {
        this.width = width;
    }

    public double getPaneHeight() {
        return height;
    }

    public void setPaneHeight(double height) {
        this.height = height;
    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }

}
