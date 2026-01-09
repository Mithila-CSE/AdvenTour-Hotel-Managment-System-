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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AdminController implements Initializable {

    @FXML
    private Button loginButton;
    
     @FXML
    private TextField username1;

    @FXML
    private PasswordField password1;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private Button backButton;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    /*public void login() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminControl.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }*/
    public void login() throws IOException {
        String user = username1.getText();
        String pass = password1.getText();
        String sql = "SELECT * FROM admin WHERE username=? and password=?";

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

                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AdminControl.fxml"));
                    primaryStage.setTitle("Admin Control");
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
    
    public void close() throws IOException{
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }
    
    public void BackPage() throws IOException{          
        Stage stage = (Stage)backButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        //primaryStage.setTitle("DivisionWiseOneDay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }

}
