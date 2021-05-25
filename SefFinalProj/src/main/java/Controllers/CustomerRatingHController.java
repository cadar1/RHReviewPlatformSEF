package Controllers;

import Start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import org.json.simple.JSONObject;

import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class CustomerRatingHController implements Initializable {
    @FXML
    private TextField restaurantName;
    @FXML
    private TextField dayText;
    @FXML
    private TextField monthText;
    @FXML
    private TextField yearText;
    @FXML
    private Slider rate;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nightsText;
    public void cancel(ActionEvent event) {
        Main.getStg().close();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void updateDatabase() throws IOException {
        if (verifyData() && verifyname()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Name", restaurantName.getText());
            jsonObject.put("Nights", nightsText.getText());
            jsonObject.put("Rating", rate.getValue());
            jsonObject.put("Day", dayText.getText());
            jsonObject.put("Month", monthText.getText());
            jsonObject.put("Year", yearText.getText());


            try {
                FileWriter file = new FileWriter("src/main/resources/HotelReviewdatabase.ndjson", true);
                file.write(jsonObject.toString());
                file.write("\n");
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getInstance().changeSceneSmall("/Home.fxml", "Home");
        }
    }
    public boolean verifyData(){
        if(parseInt(dayText.getText()) < 1 || parseInt(dayText.getText()) > 31 || parseInt(monthText.getText()) < 1 || parseInt(monthText.getText()) > 12 || parseInt(yearText.getText())> 2021){
            errorLabel.setText("Enter Valid data (0<date<31; 0<month<12; year<=2021)");
        }else {
            return true;
        }
        return false;
    }
    public boolean verifyname(){
        if(restaurantName.getText().isEmpty()){
            errorLabel.setText("Enter the name of the hotel");
        }
        else {
            return true;
        }
        return false;
    }
}


