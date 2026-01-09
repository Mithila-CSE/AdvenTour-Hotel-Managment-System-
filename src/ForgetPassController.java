/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ForgetPassController implements Initializable {

    @FXML
    private Button changePass;

    @FXML
    private TextField register_email1;

    @FXML
    private TextField register_conpass1;

    @FXML
    private TextField register_pass1;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private Button BackButton;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void ChangePass() {
        
        connect = DatabaseConnect.connectDb();
        String email1 = (String) register_email1.getText();
        Alert alert;
        try {

            if (register_email1.getText().isEmpty()
                    || register_pass1.getText().isEmpty()
                    || register_pass1.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Select The Item First");
                alert.showAndWait();

            } else {
                String check = "SELECT email FROM registration WHERE email = '"
                        + register_email1.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    String finalpass = "SELECT * FROM registration WHERE email = ?";

                    String updateData = "UPDATE registration SET password = '"
                            + register_pass1.getText() + "', conpassword = '"
                            + register_conpass1.getText() +  "' WHERE email = '" + register_email1.getText()+ "'";

                    
                    prepare = connect.prepareStatement(finalpass);
                    prepare.setString(1, email1 );
                    result = prepare.executeQuery();

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to Change Password");

                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password Changed Successfully!");
                        alert.showAndWait();

                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This Email ID: " + register_email1.getText() + " is not VALID");
                    alert.showAndWait();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    
    public void Back() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("UserLogIn.fxml"));
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
        // TODO
    }

}
