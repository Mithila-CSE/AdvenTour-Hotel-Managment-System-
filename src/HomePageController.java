/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class HomePageController implements Initializable {
    @FXML
    private Button OneDayButton;
    
    @FXML
    private Button MoreButton;
    
    @FXML
    private Button ProfileButton;
    
    @FXML
    private Button Back3Button;
    
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void Back3() throws IOException{          
        Stage stage = (Stage)Back3Button.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("UserLogIn.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();       
    }
    
    public void MyProfile() throws IOException{          
        Stage stage = (Stage)ProfileButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("ProfilePage.fxml"));
        primaryStage.setTitle("Profile");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();       
    }
    
    public void MorePlan() throws IOException{          
        Stage stage = (Stage)MoreButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("TourPlaces.fxml"));
        primaryStage.setTitle("Tour Places");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();       
    }
    
    public void close() throws IOException{
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }
}
