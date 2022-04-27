import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
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

    public Tab currentTab;

    public WebView getWebView(){
        return  webView;
    }

    public TextField getGoWeb(){
        return  goWeb;
    }

    public void initialize() {
        webView.getEngine().locationProperty().addListener((ov, oldstr, newstr) ->{
            goWeb.setText(newstr);
        });

        //Change tab title
        webView.getEngine().getLoadWorker().stateProperty().addListener((obs,oldvalue,newvalue) -> {
            if(newvalue == State.SUCCEEDED){
                currentTab.setText(webView.getEngine().getTitle());
            }
       }); 

        webView.setOnKeyPressed((key) ->{
            if (key.getCode() == KeyCode.F12) {
                showText("Source of " + webView.getEngine().getTitle(), (Stage) webView.getScene().getWindow() ,(String)webView.getEngine().executeScript("document.documentElement.outerHTML"));
            }
        }
        );

        //Zoom with Scroll Mouse (Ctrl + Scroll Mouse)
        webView.addEventFilter(ScrollEvent.SCROLL, (ScrollEvent se) -> {
            double deltaY = se.getDeltaY();
            if(se.isControlDown() && deltaY > 0) {
                zoomIn();
            } else if(se.isControlDown() && deltaY < 0) {
                zoomOut();
            }
        });
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
    void zoomIn() {
        webView.setZoom(webView.getZoom() * 1.1);
    }

    @FXML
    void zoomOut() {
        webView.setZoom(webView.getZoom() / 1.1);
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
