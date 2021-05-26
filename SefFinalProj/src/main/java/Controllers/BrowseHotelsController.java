package Controllers;

import Start.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class BrowseHotelsController {
    public void cancelOnAction(ActionEvent event) {
        Main.getStg().close();
    }
    public void leaveRatingOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/CustomerRatingH.fxml", "Rating");
    }
    public void leaveReviewOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/CustomerReviewH.fxml", "Review");
    }
    public void BrowseRestaurantsOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/BrowseHotelsMenu.fxml", "Browse");
    }
}