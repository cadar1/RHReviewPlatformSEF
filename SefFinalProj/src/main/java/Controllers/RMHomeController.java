package Controllers;

import Start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class RMHomeController {
    @FXML
    private TextField ratingText;
    //@FXML
    //private TextField reviewText;
    @FXML
    private Label ratingLabel;
    public void cancelButton(ActionEvent event) {
        Main.getStg().close();

    }
    public void addRestaurant(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/registerRestaurant.fxml", "Register Restaurant Page");
    }
    public void manageRestaurant(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/manageRestaurant.fxml", "Register Restaurant Page");
    }
    public void computeRating(ActionEvent e) {
        double avg=0;
        int nb=0;
        File database = new File("src/main/resources/RestaurantReviewdatabase.ndjson");
        try( FileReader reader= new FileReader(database))
        {
            BufferedReader buffer= new BufferedReader(reader);
            String line;

            while( (line= buffer.readLine()) != null )
            {
                Object o = new JSONParser().parse(line);
                JSONObject obj = (JSONObject) o;
                String objWish = (String) obj.get("Name");

                if( ratingText.getText().equals(objWish) )
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
           ratingLabel.setText("Average Rating: " + avg);

        }catch (Exception event)
        {
            event.printStackTrace();
        }
        //Main.getInstance().changeSceneSmall("/RMHome.fxml", "Home");
    }
}

