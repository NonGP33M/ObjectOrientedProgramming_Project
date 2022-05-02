import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class BrowserLayout {
    private VBox root;

    private Controller controller;

    public BrowserLayout() {
        controller = new Controller();
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(600, 900);

        HBox hBox = new HBox(3);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(36);
        hBox.setMinHeight(hBox.getPrefHeight());
        hBox.setPadding(new Insets(3, 5, 3, 5));
        VBox.setVgrow(hBox, Priority.NEVER);

        Button btBack = new Button();
        btBack.setPrefSize(28, 28);
        btBack.setMinSize(28, 28);
        btBack.setOnAction(controller::goBack);
        btBack.setPickOnBounds(true);
        HBox.setHgrow(btBack, Priority.NEVER);
        controller.setBtBack(btBack);
        btSetGraphic(btBack, "back");

        Button btForward = new Button();
        btForward.setPrefSize(28, 28);
        btForward.setMinSize(28, 28);
        btForward.setOnAction(controller::goForward);
        HBox.setHgrow(btForward, Priority.NEVER);
        controller.setBtForward(btForward);
        btSetGraphic(btForward, "forward");

        Button btRefresh = new Button();
        btRefresh.setPrefSize(28, 28);
        btRefresh.setMinSize(28, 28);
        btRefresh.setOnAction(controller::goRefresh);
        btRefresh.setMnemonicParsing(false);
        HBox.setHgrow(btRefresh, Priority.NEVER);
        controller.setBtRefresh(btRefresh);
        btSetGraphic(btRefresh, "Refresh");

        TextField goWeb = new TextField();
        goWeb.setOnAction(controller::goWeb);
        goWeb.setPrefHeight(26);
        HBox.setHgrow(goWeb, Priority.ALWAYS);
        controller.setGoWeb(goWeb);
        goWeb.setStyle("-fx-background-radius: 5em; -fx-padding: 0 0 0 12;");

        Button btZoomIn = new Button();
        btZoomIn.setPrefSize(28, 28);
        btZoomIn.setMinSize(28, 28);
        btZoomIn.setOnAction(controller::zoomIn);
        HBox.setHgrow(btZoomIn, Priority.NEVER);
        controller.setBtZoomIn(btZoomIn);
        btSetGraphic(btZoomIn, "zoomin");

        Button btZoomOut = new Button();
        btZoomOut.setPrefSize(28, 28);
        btZoomOut.setMinSize(28, 28);
        btZoomOut.setOnAction(controller::zoomOut);
        HBox.setHgrow(btZoomOut, Priority.NEVER);
        controller.setBtZoomOut(btZoomOut);
        btSetGraphic(btZoomOut, "zoomout");

        MenuButton btMore = new MenuButton();
        btMore.setPrefSize(28, 28);
        btMore.setMinSize(28, 28);
        MenuItem btNewTab = new MenuItem("New tab");
        btNewTab.setOnAction(controller::addNewTab);
        btNewTab.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        controller.setBtNewTab(btNewTab);
        MenuItem btShowSource = new MenuItem("Show Source Code");
        btShowSource.setOnAction(controller::showSource);
        controller.setBtShowSource(btShowSource);
        btMore.getItems().addAll(btNewTab, btShowSource);
        btSetGraphic(btMore, "more");
        ((ImageView)btMore.getGraphic()).setTranslateX(-4);

        hBox.getChildren().addAll(btBack, btForward, btRefresh, goWeb, btZoomIn, btZoomOut, btMore);
        
        WebView webView = new WebView();
        VBox.setVgrow(webView, Priority.ALWAYS);
        controller.setWebView(webView);

        root.getChildren().addAll(hBox, webView);

        controller.initialize();
    }

    private static void btSetGraphic(ButtonBase bt, String name) {
        ImageView view = new ImageView(new Image(BrowserLayout.class.getResource("icons/" + name + ".png").toString()));
        view.setFitWidth(20);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        view.setPickOnBounds(true);
        bt.setGraphic(view);
    }

    public VBox getRoot() {
        return root;
    }

    public Controller getController() {
        return controller;
    }
}