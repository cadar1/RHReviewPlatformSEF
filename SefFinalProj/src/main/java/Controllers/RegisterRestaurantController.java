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

public class RegisterRestaurantController implements Initializable {
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
    private TextField specialityField;
    @FXML
    private TextField popularField;
    @FXML
    private CheckBox dineinBox;
    @FXML
    private CheckBox takeoutBox;
    @FXML
    private CheckBox deliveryBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        jsonObject.put("Speciality", specialityField.getText());
        jsonObject.put("PopularDish", popularField.getText());
        jsonObject.put("PriceRange", priceRange.getValue());
        jsonObject.put("Dine-in:", dineinBox.isSelected());
        jsonObject.put("Takeout:", takeoutBox.isSelected());
        jsonObject.put("Delivery:", deliveryBox.isSelected());


        try {
            FileWriter file = new FileWriter("src/main/resources/Restaurantdatabase.ndjson", true);
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
        Main.getInstance().changeSceneSmall("/RMHome.fxml", "Home");
        updateDatabase();
        //}else {
        //}
    }
}
