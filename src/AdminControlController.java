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
 * @author Dell
 */
public class AdminControlController implements Initializable {
  @FXML
    private Button back;

    @FXML
    private Button hotel;

    @FXML
    private Button ticket;

    @FXML
    private Button transport;
    
    @FXML
    private Button Place;
    
      @FXML
    private Button close;

    @FXML
    private Button CustomerButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Hotel() throws IOException{
       Stage stage = (Stage)hotel.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("HotelManage.fxml"));
       
       primaryStage.setTitle("UserLogIn");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }    
    
    public void Ticket() throws IOException{
       Stage stage = (Stage)ticket.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("Ticket_Manage.fxml"));
       
       primaryStage.setTitle("UserLogIn");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }    
    
    public void Transport() throws IOException{
       Stage stage = (Stage)transport.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("RentAcar.fxml"));
       
       primaryStage.setTitle("UserLogIn");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    } 
    
    public void Back() throws IOException{          
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        primaryStage.setTitle("AdminPage");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }
    
    public void TouristPlaces() throws IOException{
       Stage stage = (Stage)Place.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("AdminPlaces.fxml"));
       
       primaryStage.setTitle("UserLogIn");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }
    
    public void TouristCustomer() throws IOException{          
        Stage stage = (Stage)CustomerButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("TotalCustomer.fxml"));
        primaryStage.setTitle("DivisionWiseOneDay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }
    
    
     public void Close() throws IOException{
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }  
    
}
