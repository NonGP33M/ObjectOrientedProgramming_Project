
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.security.Key;

public class Controller {

    @FXML
    private Button btBack;

    @FXML
    private Button btForward;

    @FXML
    private MenuItem btNewTab;

    @FXML
    private MenuItem btShowSource;

    @FXML
    private Button btRefresh;

    @FXML
    private Button btZoomIn;

    @FXML
    private Button btZoomOut;

    @FXML
    private TextField goWeb;

    @FXML
    private WebView webView;

    public Tab currentTab;

    public void initialize() {
        webView.getEngine().locationProperty().addListener((ov, oldstr, newstr) ->{
            goWeb.setText(newstr);
        });
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs,oldvalue,newvalue) -> {
            if(newvalue == State.SUCCEEDED){
                currentTab.setText(webView.getEngine().getTitle());
            }
       });

        //Shortcut for browser
        webView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.F12) {
                showSource(null);
            }

            if(ke.isControlDown()) {
                if(ke.getCode() == KeyCode.MINUS || ke.getCode() == KeyCode.UNDERSCORE) {
                    zoomOut(null);
                } else if(ke.getCode() == KeyCode.EQUALS || ke.getCode() == KeyCode.PLUS) {
                    zoomIn(null);
                }
            }

            if(ke.isAltDown()) {
                if(ke.getCode() == KeyCode.LEFT) {
                    goBack(null);
                } else if(ke.getCode() == KeyCode.RIGHT) {
                    goForward(null);
                } else if(ke.getCode() == KeyCode.R) {
                    goRefresh(null);
                }
            }

        });

        //Zoom with Scroll Mouse (Ctrl + Scroll Mouse)
        webView.addEventFilter(ScrollEvent.SCROLL, (ScrollEvent se) -> {
            double deltaY = se.getDeltaY();
            if(se.isControlDown() && deltaY > 0) {
                zoomIn(null);
            } else if(se.isControlDown() && deltaY < 0) {
                zoomOut(null);
            }
        });
    }
    

    @FXML
    void addNewTab(ActionEvent event) {
        currentTab.getTabPane().getSelectionModel().selectLast();
    }

    @FXML
    void showSource(ActionEvent event) {
        showText("Source of " + webView.getEngine().getTitle(), (Stage) webView.getScene().getWindow() ,(String)webView.getEngine().executeScript("document.documentElement.outerHTML"));
    }

    @FXML
    void goBack(ActionEvent event) {
        webView.getEngine().getHistory().go(-1);
    }

    @FXML
    void goForward(ActionEvent event) {
        webView.getEngine().getHistory().go(1);
    }

    @FXML
    void goRefresh(ActionEvent event) {
        webView.getEngine().reload();
    }

    @FXML
    void goWeb(ActionEvent event) {
        String URL = goWeb.getText();

        if (!URL.contains(".")) {
            webView.getEngine().load("https://www.google.com/search?q=" + URL);
            return;
        }
        if (!URL.startsWith("http://") && !URL.startsWith("https://")) {
            URL = "https://" + URL;
        }

        webView.getEngine().load(URL);
    }

    @FXML
    void zoomIn(ActionEvent event) {
        if(webView.getZoom() <= 1.9) {
            webView.setZoom(webView.getZoom() * 1.1);
        }
    }

    @FXML
    void zoomOut(ActionEvent event) {
        if(webView.getZoom() >= 0.25) {
            webView.setZoom(webView.getZoom() / 1.1);
        }
    }

    public void showText(String title, Stage window, String text) {
        TextArea root = new TextArea(text);
        Scene secondScene = new Scene(root, 600, 600);
        Stage secondWindow = new Stage();
        secondWindow.setTitle(title);
        secondWindow.setScene(secondScene);
        secondWindow.initOwner(window);
        secondWindow.show();
    }
}
