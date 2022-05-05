import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(900, 600);
        tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);

        // add new tad
        Tab add = new Tab();
        add.setClosable(false);
        add.setId("addTab");
        add.setGraphic(new ImageView(new Image(getClass().getResource("icons/add.png").toString(),14,14,true,false)));
        tabPane.getTabs().add(add);
        tabPane.getTabs().add(0, createNewTab("New tab"));
        tabPane.getSelectionModel().select(0);
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab == add) {
                Tab t = createNewTab("New tab");
                tabPane.getTabs().add(tabPane.getTabs().size()-1, t);
                tabPane.getSelectionModel().select(t);
            }
        });

        Scene scene = new Scene(tabPane);
        primaryStage.getIcons().add(new Image("icons/icon.png"));
        primaryStage.setTitle("The World Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Tab createNewTab(String name){
        Tab tab = new Tab(name);
        BrowserLayout bl = new BrowserLayout();
        Controller con = bl.getController();
        con.currentTab = tab;
        tab.setContent(bl.getRoot());
        return tab;
    }
}