package Controllers;

import Start.Main;
import javafx.event.ActionEvent;

import java.io.IOException;

public class BrowseRestaurants {
    public void cancelOnAction(ActionEvent event) {
        Main.getStg().close();
    }
    public void leaveRatingOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/CustomerRating.fxml", "Rating");
    }
    public void leaveReviewOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/CustomerReview.fxml", "Review");
    }
    public void BrowseRestaurantsOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/BrowseRestaurantsMenu.fxml", "Browse");
    }
}
