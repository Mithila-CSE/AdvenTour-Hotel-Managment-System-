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

/**
 * FXML Controller class
 *
 * @author My Lenovo
 */
public class HotelManageController implements Initializable {
       @FXML
    private Button back;

    @FXML
    private Button close;

    @FXML
    private Button customerButton;

    @FXML
    private Button room;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void Room() throws IOException{          
        Stage stage = (Stage)room.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("BookHotel2.fxml"));
        primaryStage.setTitle("DivisionWiseOneDay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }
    
     public void Customers() throws IOException{
       Stage stage = (Stage)customerButton.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("BookHotel3.fxml"));      
       primaryStage.setTitle("Hotel");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }
     
     public void Back() throws IOException{          
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("AdminControl.fxml"));
        primaryStage.setTitle("AdminPage");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }
     
      public void Close() throws IOException{
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    
}
