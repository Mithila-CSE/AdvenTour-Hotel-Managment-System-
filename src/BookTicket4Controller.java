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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class BookTicket4Controller implements Initializable {

    @FXML
    private TableView<TicketData> Ticket_table;

    @FXML
    private Button customerBus_BackButton;

    @FXML
    private Button customerBus_closeButton;

    @FXML
    private TableColumn<TicketData, String> ticket_busId;

    @FXML
    private TableColumn<TicketData, String> ticket_date;

    @FXML
    private TableColumn<TicketData, String> ticket_firstName;

    @FXML
    private TableColumn<TicketData, String> ticket_from;

    @FXML
    private TableColumn<TicketData, String> ticket_lastName;

    @FXML
    private TableColumn<TicketData, String> ticket_phone;

    @FXML
    private TableColumn<TicketData, String> ticket_price;

    @FXML
    private TableColumn<TicketData, String> ticket_seat;

    @FXML
    private TableColumn<TicketData, String> ticket_to;

    @FXML
    private TableColumn<TicketData, String> ticket_type;

    @FXML
    private TableColumn<TicketData, String> ticket_satus;

    @FXML
    private TextField Selected_status;

    @FXML
    private TextField selected_busid;

    private Connection connect = null;
    private PreparedStatement prepare = null;
    private ResultSet result = null;
    private Statement statement;

    public ObservableList<TicketData> refreshData() {
        ObservableList<TicketData> TicketListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ticketcustomer";
        connect = DatabaseConnect.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            TicketData ticketD;
            while (result.next()) {
                ticketD = new TicketData(result.getString("fname"),
                        result.getString("lname"),
                        result.getString("phoneno"),
                        result.getString("tfrom"),
                        result.getString("tto"),
                        result.getInt("busid"),
                        result.getString("type"),
                        result.getInt("seatnum"),
                        result.getDouble("price"),
                        result.getDate("date"),
                        result.getString("BookingStatus"));

                TicketListData.add(ticketD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return TicketListData;
    }

    private ObservableList<TicketData> availableTicketListData;

    public void ShowTicketData() {

        availableTicketListData = refreshData();

        ticket_firstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        ticket_lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        ticket_phone.setCellValueFactory(new PropertyValueFactory<>("phonenum"));
        ticket_from.setCellValueFactory(new PropertyValueFactory<>("from"));
        ticket_to.setCellValueFactory(new PropertyValueFactory<>("to"));
        ticket_busId.setCellValueFactory(new PropertyValueFactory<>("budId"));
        ticket_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        ticket_seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        ticket_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticket_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        ticket_satus.setCellValueFactory(new PropertyValueFactory<>("status"));

        Ticket_table.setItems(availableTicketListData);
        // searchTable_From.setCellValueFactory(new PropertyValueFactory<>("from"));
    }

    /* public void availableSelectUserData() {
        TicketData busD = Ticket_table.getSelectionModel().getSelectedItem();
        int num = Ticket_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        Username_delete.setText(busD.getUsername());
        
    }*/
    public void availableSatusUpdate() {
        String updateData = "UPDATE ticketcustomer SET BookingStatus = '"
                + Selected_status.getText() + "'";

        connect = DatabaseConnect.connectDb();

        Alert alert;
        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Status: " + selected_busid.getText() + "?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                prepare = connect.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                //availableBShowBusData();
                //availableBusReset();
            } else {
                return;
            }

        
    }
    catch (Exception e

    
        ) {
            e.printStackTrace();
    }
}

public void availableSelectUserData() {
        TicketData busD = Ticket_table.getSelectionModel().getSelectedItem();
        int num = Ticket_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        selected_busid.setText(String.valueOf(busD.getBudId()));
        Selected_status.setText(busD.getStatus());
        
    }
    
    
    @FXML
private void Back(ActionEvent event) {
        Stage stage = (Stage)customerBus_BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("Ticket_Manage.fxml"));

} catch (IOException ex) {
            Logger.getLogger(Ticket_ManageController.class  

.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }  
    
    public void Close() throws IOException{
        Stage stage = (Stage)customerBus_closeButton.getScene().getWindow();
        stage.close();
    }     
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
       ShowTicketData();
    } 
    
}
