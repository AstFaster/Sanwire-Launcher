package fr.sanwire.launcher.core.ui.components;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public interface IPlace {

    void setTop(Node node);
    void setBottom(Node node);
    void setBaseline(Node node);
    void setLeft(Node node);
    void setRight(Node node);
    void setCenterV(Node node);
    void setCenterH(Node node);

    default void setTakeAllPlace(Node node){
        GridPane.setVgrow(node, Priority.ALWAYS);
        GridPane.setHgrow(node, Priority.ALWAYS);
    }
}
