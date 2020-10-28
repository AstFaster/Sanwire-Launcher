package fr.sanwire.launcher.core.ui.panels;

import fr.sanwire.launcher.core.SanwireLauncher;
import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.components.IPlace;
import fr.sanwire.launcher.core.utils.logger.LogType;
import fr.sanwire.launcher.core.utils.logger.Logger;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractPanel implements IPanel, IPlace {

    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;
    protected final Logger logger = SanwireLauncher.getInstance().getLogger();

    @Override
    public void init(PanelManager panelManager) {
        this.panelManager = panelManager;
        GridPane.setHgrow(this.layout, Priority.ALWAYS);
        GridPane.setVgrow(this.layout, Priority.ALWAYS);
    }

    protected void openUrl(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
            this.logger.log("Opening " + url + ".", LogType.INFO);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GridPane getLayout() {
        return layout;
    }

    @Override
    public abstract String getName();

    @Override
    public void setTop(Node node) {
        GridPane.setValignment(node, VPos.TOP);
    }

    @Override
    public void setBottom(Node node) {
        GridPane.setValignment(node, VPos.BOTTOM);
    }

    @Override
    public void setBaseline(Node node) {
        GridPane.setValignment(node, VPos.BASELINE);
    }

    @Override
    public void setLeft(Node node) {
        GridPane.setHalignment(node, HPos.LEFT);
    }

    @Override
    public void setRight(Node node) {
        GridPane.setHalignment(node, HPos.RIGHT);
    }

    @Override
    public void setCenterV(Node node) {
        GridPane.setValignment(node, VPos.CENTER);
    }

    @Override
    public void setCenterH(Node node) {
        GridPane.setHalignment(node, HPos.CENTER);
    }
}
