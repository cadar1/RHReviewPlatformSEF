package Start;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    public static Stage getStg() {
        return stg;
    }

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg= primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/HMHome.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    private static Main instance;

    public Main(){
        instance= this;
    }

    public static Main getInstance(){
        return instance;
    }
    public void changeSceneSmall(String fxml, String title) throws IOException
    {
        Parent pane= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.setScene(new Scene(pane, 600, 400));
        stg.setTitle(title);
        stg.getScene().setRoot(pane);
    }
    public void changeSceneBig(String fxml, String title) throws IOException
    {
        Parent pane= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.setScene(new Scene(pane, 520, 400));
        stg.setTitle(title);
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

