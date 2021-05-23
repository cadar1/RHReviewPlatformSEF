package Controllers;

import Start.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;


public class RegiserController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox role;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;
    private static String encodePassword ( String username, String password )
    {
        MessageDigest md = getMessageDigest();
        md.update(username.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest()
    {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e)
        {
            throw new IllegalStateException("SHA-512 does not exist!");
        }

        return md;
    }
    public void updateDatabase()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Role", role.getValue());
        jsonObject.put("Full name", name.getText());
        jsonObject.put("Username", username.getText());
        jsonObject.put("Password", encodePassword(username.getText(), password.getText()));

        try {
            FileWriter file = new FileWriter("src/main/resources/database.ndjson", true);
            file.write(jsonObject.toString());
            file.write("\n");
            file.flush();
            file.close();
        } catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
    public void doneButton() throws IOException {
        if (verifyUser()) {
            Main.getInstance().changeSceneSmall("/Home.fxml", "Home");
            updateDatabase();
        }else {
            errorMessage.setText("Username taken. Try again");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().removeAll(role.getItems());
        role.getItems().addAll("Customer", "Restaurant Manager", "Hotel Manager");
        role.getSelectionModel().selectFirst();

    }
    public boolean verifyUser()
    {
        File database= new File("src/main/resources/database.ndjson");
        try( FileReader reader= new FileReader(database))
        {
            BufferedReader buffer= new BufferedReader(reader);
            String line;

            while( (line= buffer.readLine()) != null )
            {
                Object o= new JSONParser().parse(line);
                JSONObject obj= (JSONObject) o;
                String objUsername= (String) obj.get("Username");
                String objPassword= (String) obj.get("Password");

                if( username.getText().equals(objUsername)) {

                    return false;

                }
            }
        }catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }

        return true;
    }
}
