package fr.sanwire.launcher.core.ui.panels.includes;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import fr.sanwire.launcher.core.Main;
import fr.sanwire.launcher.core.SanwireLauncher;
import fr.sanwire.launcher.core.ui.PanelManager;
import fr.sanwire.launcher.core.ui.panels.AbstractPanel;
import fr.sanwire.launcher.core.utils.References;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TopPanel extends AbstractPanel {

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        this.layout.setOnMousePressed(e -> {
            xOffset = panelManager.getStage().getX() - e.getScreenX();
            yOffset = panelManager.getStage().getY() - e.getScreenY();
        });

        this.layout.setOnMouseDragged(e -> {
            panelManager.getStage().setX(e.getScreenX() + xOffset);
            panelManager.getStage().setY(e.getScreenY() + yOffset);
        });

        this.layout.setOnMouseReleased(e -> {
            if (panelManager.getStage().getY() <= 0) {
                this.panelManager.getStage().centerOnScreen();
                this.panelManager.getStage().setMaximized(true);
            }
        });

        this.layout.getChildren().addAll(this.addTopBarButtons(), this.addTitle());
    }

    private Label addTitle(){
        final Label title = new Label(References.LAUNCHER_NAME.toUpperCase());
        Font font = Font.loadFont(Main.class.getResourceAsStream("/resources/font/franklin-gothic-heavy-regular.ttf"), 13.0d);
        title.setFont(font);
        title.setStyle("-fx-text-fill: #c5bdca;");
        title.setTranslateX(7.0);

        this.setTakeAllPlace(title);

        return title;
    }

    private GridPane addTopBarButtons(){
        final GridPane topBarButtons = new GridPane();

        topBarButtons.setMaxWidth(100.0d);
        topBarButtons.setMinWidth(100.0d);

        this.setRight(topBarButtons);

        final MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        final MaterialDesignIconView maximize = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MAXIMIZE);
        final MaterialDesignIconView minimize = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);

        GridPane.setVgrow(close, Priority.ALWAYS);
        GridPane.setVgrow(maximize, Priority.ALWAYS);
        GridPane.setVgrow(minimize, Priority.ALWAYS);

        close.setFill(Color.rgb(197, 189, 202));
        close.setOpacity(0.70d);
        close.setSize("18.0px");
        close.setOnMouseEntered(e -> close.setOpacity(1.0d));
        close.setOnMouseExited(e -> close.setOpacity(0.70d));
        close.setOnMouseClicked(e -> SanwireLauncher.getInstance().shutdown());
        close.setTranslateX(70.0d);

        maximize.setFill(Color.rgb(197, 189, 202));
        maximize.setOpacity(0.70d);
        maximize.setSize("18.0px");
        maximize.setOnMouseEntered(e -> maximize.setOpacity(1.0d));
        maximize.setOnMouseExited(e -> maximize.setOpacity(0.70d));
        maximize.setOnMouseClicked(e -> this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized()));
        maximize.setTranslateX(35.0d);

        minimize.setFill(Color.rgb(197, 189, 202));
        minimize.setOpacity(0.70d);
        minimize.setSize("16.0px");
        minimize.setOnMouseEntered(e -> minimize.setOpacity(1.0d));
        minimize.setOnMouseExited(e -> minimize.setOpacity(0.70d));
        minimize.setOnMouseClicked(e -> this.panelManager.getStage().setIconified(true));
        minimize.setTranslateX(-5.0d);

        topBarButtons.getChildren().addAll(close, maximize, minimize);

        return topBarButtons;
    }

    @Override
    public String getName() {
        return "";
    }
}
