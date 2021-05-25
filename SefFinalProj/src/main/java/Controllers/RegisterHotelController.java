package Controllers;

import Start.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterHotelController implements Initializable {
    @FXML
    private ChoiceBox stars;
    @FXML
    private ChoiceBox priceRange;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField nextEvent;
    @FXML
    private CheckBox poolBox;
    @FXML
    private CheckBox wifiBox;
    @FXML
    private CheckBox gymBox;
    @FXML
    private CheckBox roomserviceBox;
    @FXML
    private CheckBox parkingBox;
    @FXML
    private CheckBox petBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stars.getItems().removeAll(stars.getItems());
        stars.getItems().addAll("1*", "2*", "3*", "4*", "5*");
        stars.getSelectionModel().selectFirst();
        priceRange.getItems().removeAll(priceRange.getItems());
        priceRange.getItems().addAll("$", "$$", "$$$");
        priceRange.getSelectionModel().selectFirst();
    }
    public void updateDatabase()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name", nameField.getText());
        jsonObject.put("Address", addressField.getText());
        jsonObject.put("Phone", phoneField.getText());
        jsonObject.put("City", cityField.getText());
        jsonObject.put("Country", countryField.getText());
        jsonObject.put("Next event:", nextEvent.getText());
        jsonObject.put("Stars", stars.getValue());
        jsonObject.put("PriceRange", priceRange.getValue());
        jsonObject.put("Pool",poolBox.isSelected());
        jsonObject.put("Wi-Fi",wifiBox.isSelected());
        jsonObject.put("Gym",gymBox.isSelected());
        jsonObject.put("Room-Service",roomserviceBox.isSelected());
        jsonObject.put("Parking",parkingBox.isSelected());
        jsonObject.put("Pets",petBox.isSelected());


        try {
            FileWriter file = new FileWriter("src/main/resources/Hoteldatabase.ndjson", true);
            file.write(jsonObject.toString());
            file.write("\n");
            file.flush();
            file.close();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
    public void doneButtonOnAction() throws IOException {
       // if () {
            Main.getInstance().changeSceneSmall("/HMHome.fxml", "Home");
            updateDatabase();
        //}else {
        //}
    }
}
