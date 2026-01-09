/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
public class Ticket_ManageController implements Initializable {

    @FXML
    private Button Buses;
    @FXML
    private Button customer;
    @FXML
    private Button back;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AvailBus(ActionEvent event) {
        Stage stage = (Stage)Buses.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("BookTicket2.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Ticket_ManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();  
    }

    @FXML
    private void Customers(ActionEvent event) {
        Stage stage = (Stage)customer.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("BookTicket4.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Ticket_ManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();  
    }

    @FXML
    private void Back(ActionEvent event) {
        Stage stage = (Stage)back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("AdminControl.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Ticket_ManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }  
    
    public void Close() throws IOException{
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    } 
}
