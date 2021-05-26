package Controllers;

import Start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HMHomeController {
    @FXML
    private TextField ratingBox;
    //@FXML
    //private TextField reviewText;
    @FXML
    private Label ratingsLabel;
    public void cancelButton(ActionEvent event) {
        Main.getStg().close();

    }
    public void addHotelOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/registerHotel.fxml", "Register Hotel Page");
    }
    public void manageHotelOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/manageHotel.fxml", "Register Hotel Page");
    }
    public void computeRating(ActionEvent e) {
        double avg=0;
        int nb=0;
        File database = new File("src/main/resources/HotelReviewdatabase.ndjson");
        try( FileReader reader= new FileReader(database))
        {
            BufferedReader buffer= new BufferedReader(reader);
            String line;

            while( (line= buffer.readLine()) != null )
            {
                Object o = new JSONParser().parse(line);
                JSONObject obj = (JSONObject) o;
                String objWish = (String) obj.get("Name");

                if( ratingBox.getText().equals(objWish) )
                {
                    double rating = (double) obj.get("Rating");
                    //System.out.println(obj.get("Rating"));
                    nb++;
                    avg = avg + rating;
                    //System.out.println(avg);
                    //System.out.println(ratingText.getText());
                }
            }
            avg=avg/nb;
            ratingsLabel.setText("Average Rating: " + avg);

        }catch (Exception event)
        {
            event.printStackTrace();
        }
        //Main.getInstance().changeSceneSmall("/RMHome.fxml", "Home");
    }
}

