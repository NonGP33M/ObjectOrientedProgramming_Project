import javax.swing.event.ChangeListener;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

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
        webView.getEngine().locationProperty().addListener((ov, oldstr, newstr) ->{
            goWeb.setText(newstr);
            
        });
    }

    // }
    /*
     * TextArea root = new TextArea(tfURL);
     * Scene scene = new Scene(root, 600, 400);
     * scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
     * if(key.getCode() == KeyCode.F12) {
     * showText("Soure of " + webView.getEngine().getTitle(),
     * webView.getEngine().window, (String)
     * webView.executeScript("document.documentElement.outerHTML"));
     * }
     * });
     */

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
