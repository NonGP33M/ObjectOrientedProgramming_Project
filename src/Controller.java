import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

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

    public void initialize(){
        String tfURL = "";
        webView.getEngine().load(tfURL);
        
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
    
    }

    @FXML
    void zoomIn(ActionEvent event) {
        webView.setZoom(webView.getZoom() + 10);
    }

    @FXML
    void zoomOut(ActionEvent event) {
        webView.setZoom(webView.getZoom() - 10);
    }

}
