import java.net.URL;

import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Button btBack;

    @FXML
    private Button btForward;

    @FXML
    private Button btOption;

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

    public void initialize() {
        String tfURL = "";
        webView.getEngine().load(tfURL);
        webView.getEngine().load("http://" + webView.getEngine().getHistory());
    
       // }
        /*TextArea root = new TextArea(tfURL);
        Scene scene = new Scene(root, 600, 400);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.F12) {
                showText("Soure of " + webView.getEngine().getTitle(), webView.getEngine().window, (String) webView.executeScript("document.documentElement.outerHTML"));
            }
      });*/
    }

    @FXML
    void Option(ActionEvent event) {

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
        String tfURL = goWeb.getText();
        if (tfURL.startsWith("http://") || tfURL.startsWith("https://")) {
            webView.getEngine().load(tfURL);
            System.out.println("if");
        }
        else if(!tfURL.startsWith("http://") && !tfURL.startsWith("https://")){
            webView.getEngine().load("https://" + tfURL);
            System.out.println("el if");
        }
        else{
            webView.getEngine().load("https://www.google.com/search?q=" + goWeb.getText());
            System.out.println("else");
        }
    }

    @FXML
    void zoomIn(ActionEvent event) {
        webView.setZoom(webView.getZoom() + 0.1);
    }

    @FXML
    void zoomOut(ActionEvent event) {
        webView.setZoom(webView.getZoom() - 0.1);
    }

    public void showText(String title, Stage window, String text) {
        TextArea root = new TextArea(text);
        Scene scene = new Scene(root, 600, 400);
        Stage secondWindow = new Stage();
        secondWindow.setTitle(title);
        secondWindow.setScene(scene);
        secondWindow.initOwner(window);
        secondWindow.show();
    }
}
