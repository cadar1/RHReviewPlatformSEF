package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Start.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterpasswordField;
    @FXML
    private Button registerButton;

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

    @Override
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

    public void RegisterButtonOnAction(ActionEvent event) throws IOException {
        Main.getInstance().changeSceneBig("/register.fxml", "Home Page");

    }

    public void CancelButtonOnAction(ActionEvent event) {
        Main.getStg().close();

    }
    public void verifyUser()
    {
        File database= new File("src/main/resources/database.ndjson");
        Boolean flag=false;
        try( FileReader reader= new FileReader(database))
        {
            BufferedReader buffer= new BufferedReader(reader);
            String line;

            while( (line= buffer.readLine()) != null ) {
                Object o = new JSONParser().parse(line);
                JSONObject obj = (JSONObject) o;
                String objRole = (String) obj.get("Role");
                String objUsername = (String) obj.get("Username");
                String objPassword = (String) obj.get("Password");
                String encodedPass = encodePassword(usernameTextField.getText(), enterpasswordField.getText());

                if (usernameTextField.getText().equals(objUsername)) {
                    flag = true;
                    System.out.println(encodedPass);
                    System.out.println(objPassword);
                    System.out.println(encodedPass.compareTo(objPassword));
                    //if username is found and pass correct
                    if (encodedPass.equals(objPassword)) {
                        loginMessageLabel.setText("Success!");
                        if(objRole.equals("Customer")) {
                            Main.getInstance().changeSceneSmall("/Home.fxml", "Home Page");
                        }
                        if(objRole.equals("Restaurant Manager")) {
                            Main.getInstance().changeSceneSmall("/RMHome.fxml", "Home Page");
                        }
                        if(objRole.equals("Hotel Manager")) {
                            Main.getInstance().changeSceneSmall("/HMHome.fxml", "Home Page");
                        }
                    } else {
                        //if password is wrong
                        loginMessageLabel.setText("Wrong password.");

                    }
                }
            }
        }catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
            if( flag == false ) {
                //if username is not found
                loginMessageLabel.setText("Username does not exist!");
            }

    }
    public void handleLoginButton() throws IOException{
        if( usernameTextField.getText().isEmpty() || enterpasswordField.getText().isEmpty() ){
            loginMessageLabel.setText("Please enter your data!");
        } else{
            verifyUser();
        }
    }
}
