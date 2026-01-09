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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class UserLogInController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private Button LogIn2Button;

    @FXML
    private Button closeButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink forgot;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void Back() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void LogIn2() throws IOException {
        String user = username.getText();
        String pass = password.getText();
        String sql = "SELECT * FROM reistration1 WHERE username=? and pass=?";

        connect = DatabaseConnect.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user);
            prepare.setString(2, pass);

            result = prepare.executeQuery();

            Alert alert;

            if (user.isEmpty() || pass.isEmpty()) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All The Blanks");
                alert.showAndWait();
            } else {

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Logged In");
                    alert.showAndWait();

                    Stage stage = (Stage) LogIn2Button.getScene().getWindow();
                    stage.close();
                    UserProfileData.setUserName(user);
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                    primaryStage.setTitle("Home Page");
                    primaryStage.setScene(new Scene(root));
                   // primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.show();

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("UserName Or Password Incorrect");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            System.out.println("Rejuana susmi");
        }
    }

    public void Forgot() throws IOException {
        Stage stage = (Stage) forgot.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ForgetPass.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    
    
    public void close() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
