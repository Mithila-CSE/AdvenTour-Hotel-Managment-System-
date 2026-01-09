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
public class TransportPage1Controller implements Initializable {

    @FXML
    private Button Back4Button;
    
    @FXML
    private Button BusButton;
    
    @FXML
    private Button closeButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void Back4() throws IOException{          
        Stage stage = (Stage)Back4Button.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("TourPlaces.fxml"));
        primaryStage.setTitle("DivisionWiseOneDay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }
    
    
    public void BusPage() throws IOException{          
        Stage stage = (Stage)BusButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root  = FXMLLoader.load(getClass().getResource("CustomerTicket.fxml"));
        primaryStage.setTitle("Customer Ticket Booking");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();       
    }
    
    public void close() throws IOException{
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }
    
}
