/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class EditProfileController implements Initializable {
    
    @FXML
    private Hyperlink hyper;
    @FXML
    private TextField Email;

    @FXML
    private TextField Firstname;

    @FXML
    private TextField Phone;

    @FXML
    private Button finalButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private TextField ChangeImage;

    @FXML
    private TextField lastName;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    
    
    
    public void Changeprofile() {

        String user = UserProfileData.getUserName();

        String sql = "SELECT * FROM reistration1 WHERE username = '"
                + user + "'";

        try {

            connect = DatabaseConnect.connectDb();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                Firstname.setText((String)result.getString("firstname"));
                lastName.setText((String)result.getString("lastname"));
                Email.setText((String)result.getString("email"));
                Phone.setText((String)result.getString("phone"));
              //  ChangeImage.setText((String)result.getString("image"));
                

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void availableProfileUpdate() {
        String updateData = "UPDATE reistration1 SET firstname = '"
                + Firstname.getText()
                + "', lastname = '" + lastName.getText()
                + "', email = '" + Email.getText()
                + "', phone = '" + Phone.getText()
                + "' WHERE username = '" + UserProfileData.getUserName() + "'";

        connect = DatabaseConnect.connectDb();

        Alert alert;
        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Bus ID: " + UserProfileData.getUserName() + "?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                prepare = connect.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void Back() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        primaryStage.setTitle("AdminPage");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

      public void Close() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Changeprofile();
    }    
    
}
