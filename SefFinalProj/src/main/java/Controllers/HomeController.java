package Controllers;

import Start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button restaurantsButton;
    private Button hotelsButton;

    public void hotelsButton(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneBig("/BrowseHotels.fxml", "Restaurants Page");

    }
    public void restaurantsButton(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneBig("/BrowseRestaurants.fxml", "Restaurants Page");

    }
}
