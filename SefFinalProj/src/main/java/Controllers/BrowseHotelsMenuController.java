package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowseHotelsMenuController implements Initializable {

    @FXML
    private ListView<String> restaurantList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        File database = new File("src/main/resources/Hoteldatabase.ndjson");
        try (FileReader reader = new FileReader(database)) {
            BufferedReader buffer = new BufferedReader(reader);
            String line;

            while ((line = buffer.readLine()) != null) {
                Object o = new JSONParser().parse(line);
                JSONObject obj = (JSONObject) o;
                String objWish = (String) obj.get("Name");
                String objWish1 = (String) obj.get("Address");
                String objWish2 = (String) obj.get("Phone");
                String objWish3 = (String) obj.get("City");
                String objWish4 = (String) obj.get("Country");
                restaurantList.getItems().add(objWish + ", " + objWish1 + ", " + objWish2 + ", " + objWish3 + ", " + objWish4);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
