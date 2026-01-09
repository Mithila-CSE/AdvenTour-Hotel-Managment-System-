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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ProfilePageController implements Initializable {

    @FXML
    private Label email;

    @FXML
    private Label fname;

    @FXML
    private Label lname;

    @FXML
    private Label phone;

    @FXML
    private Label uname;

    @FXML
    private Label Lgender;

    @FXML
    private Circle ProfileImage;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button BackButton;
    
    @FXML
    private Button closeButton;
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private File file;
    private Image image1;

    public void profile() {

        String user = UserProfileData.getUserName();

        String sql = "SELECT * FROM reistration1 WHERE username = '"
                + user + "'";

        try {

            connect = DatabaseConnect.connectDb();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                fname.setText(result.getString("firstname"));
                lname.setText(result.getString("lastname"));
                uname.setText(result.getString("username"));
                email.setText(result.getString("email"));
                phone.setText(result.getString("phone"));
                Lgender.setText(result.getString("gender"));

                file = new File(result.getString("image"));
                image1 = new Image(file.toURI().toString());
                ProfileImage.setFill(new ImagePattern(image1));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void letsEdit() throws IOException{          
        Stage stage = (Stage)editButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
        primaryStage.setTitle("edit profile");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();       
    }
    
    public void Back() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
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
        //System.out.println(UserProfileData.getUserName());
        profile();
    }

}
