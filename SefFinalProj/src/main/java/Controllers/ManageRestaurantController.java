package Controllers;

import Start.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.control.TextField;
import java.io.*;

public class ManageRestaurantController {
    @FXML
    private TextField eventnext;
    @FXML
    private TextField namehotel;
    @FXML
    private TextField deleteThis;
    public void back(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneSmall("/RMHome.fxml", "Home");
    }
    public void changeEvent(ActionEvent event) throws IOException, ParseException {
        File database = new File("src/main/resources/Restaurantdatabase.ndjson");
        try( FileReader reader= new FileReader(database))
        {
            BufferedReader buffer= new BufferedReader(reader);
            String line;

            while( (line= buffer.readLine()) != null )
            {
                Object o = new JSONParser().parse(line);
                JSONObject obj = (JSONObject) o;

                String objWish = (String) obj.get("Name");
                //System.out.println(objWish);
                if( namehotel.getText().equals(objWish) )
                {

                    obj.remove("PopularDish");
                    obj.put("PopularDish", eventnext.getText());
                    FileWriter file = new FileWriter("src/main/resources/Restaurantdatabase.ndjson", true);
                    file.write(obj.toString());
                    file.write("\n");
                    file.flush();
                    file.close();
                    break;
                }
            }
        }catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
        deleteRestaurantbyEvent();
        Main.getInstance().changeSceneSmall("/RMHome.fxml", "Home");
    }

    public void deleteRestaurantbyEvent() throws IOException, ParseException {
        BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/Restaurantdatabase.ndjson"));

        String line= bufReader.readLine();
        FileWriter file = new FileWriter("src/main/resources/Restaurantdatabase.ndjson");
        while ( line != null )
        {
            Object o = new JSONParser().parse(line);
            JSONObject obj = (JSONObject) o;
            JSONObject objj = (JSONObject) o;
            String objWish = (String) obj.get("Name");
            String objEv = (String) objj.get("PopularDish");
            System.out.println(objEv);
            System.out.println(objWish);

            if( namehotel.getText().equals(objWish) && !(eventnext.getText().equals(objEv))) {
            }
            else {
                file.write(line + "\n");
                file.flush();
            }

            line= bufReader.readLine();
        }

        file.close();
        bufReader.close();
    }
    public void deleteHotel() throws IOException, ParseException {
        BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/Restaurantdatabase.ndjson"));

        String line= bufReader.readLine();
        FileWriter file = new FileWriter("src/main/resources/Restaurantdatabase.ndjson");
        while ( line != null )
        {
            Object o = new JSONParser().parse(line);
            JSONObject obj = (JSONObject) o;
            String objWish = (String) obj.get("Name");

            System.out.println(objWish);

            if(!(deleteThis.getText().equals(objWish))) {

                file.write(line + "\n");
                file.flush();
            }

            line= bufReader.readLine();
        }

        file.close();
        bufReader.close();
    }

}

