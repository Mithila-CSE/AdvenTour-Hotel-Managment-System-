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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class CustomerTicketController implements Initializable {

    @FXML
    private ComboBox<?> bookingTicket_busId1;

    @FXML
    private ComboBox<?> bookingTicket_busLocation;

    @FXML
    private ComboBox<?> bookingTicket_busLocation1;

    @FXML
    private ComboBox<?> bookingTicket_Ac;

    @FXML
    private ComboBox<?> bookingTicket_ticketNum;

    @FXML
    private DatePicker bookingTicket_date;

    @FXML
    private Label bookingTicket_firstName;

    @FXML
    private Label bookingTicket_lastName;

    @FXML
    private Label bookingTicket_phoneNum;

    @FXML
    private Label justPrice;

    @FXML
    private Button backButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label practice;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void busIdList() {

        String busD = "SELECT * FROM lastbus WHERE status = 'Available'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(busD);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("bus_id"));
            }
            bookingTicket_busId1.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void busLocationList() {

        String locationL = "SELECT * FROM lastbus WHERE status = 'Available'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(locationL);
            result = prepare.executeQuery();

            ObservableList listL = FXCollections.observableArrayList();

            while (result.next()) {

                listL.add(result.getString("frombus"));
            }
            bookingTicket_busLocation.setItems(listL);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void busToList() {

        String locationL = "SELECT * FROM lastbus WHERE status = 'Available'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(locationL);
            result = prepare.executeQuery();

            ObservableList listL = FXCollections.observableArrayList();

            while (result.next()) {

                listL.add(result.getString("tobus"));
            }
            bookingTicket_busLocation1.setItems(listL);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] listAc = {"Ac", "Non-Ac"};

    public void typeAc() {

        List<String> AList = new ArrayList<>();

        for (String data : listAc) {
            AList.add(data);
        }

        ObservableList listType = FXCollections.observableArrayList(AList);
        bookingTicket_Ac.setItems(listType);

    }

    public void ticketNumList() {
        List<String> listTicket = new ArrayList<>();
        for (int q = 1; q <= 40; q++) {
            listTicket.add(String.valueOf(q));
        }

        ObservableList listTi = FXCollections.observableArrayList(listTicket);
        bookingTicket_ticketNum.setItems(listTi);
    }

    private double priceData = 0;
    private double totalP = 0;

    public void customerTicketBook() {
        String insertCustomerData = "INSERT INTO  ticketcustomer(fname,lname,phoneno,tfrom,tto,busid,type,seatnum,price,date,BookingStatus)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        connect = DatabaseConnect.connectDb();

        try {
            String firstName = bookingTicket_firstName.getText();
            String lastName = bookingTicket_lastName.getText();
            String phoneNumber = bookingTicket_phoneNum.getText();
            String date = String.valueOf(bookingTicket_date.getValue());
            String busId = (String) bookingTicket_busId1.getSelectionModel().getSelectedItem();
            String location = (String) bookingTicket_busLocation.getSelectionModel().getSelectedItem();
            String location1 = (String) bookingTicket_busLocation1.getSelectionModel().getSelectedItem();
            String type = (String) bookingTicket_Ac.getSelectionModel().getSelectedItem();
            String ticketNum = (String) bookingTicket_ticketNum.getSelectionModel().getSelectedItem();
            String Status   = "Pending";

            Alert alert;

            if (busId == null || location == null || location1 == null
                    || type == null || ticketNum == null || date.isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Messages");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();

            } else {

                String totalPrice = "SELECT price FROM lastbus WHERE frombus = '"
                        + location + "'";

                try {

                    connect = DatabaseConnect.connectDb();

                    prepare = connect.prepareStatement(totalPrice);
                    result = prepare.executeQuery();

                    if (result.next()) {
                        priceData = result.getDouble("price");
                    }

                    if (type == "Ac") {
                        totalP = (priceData + 100);
                    } else if (type == "Non-Ac") {
                        totalP = priceData;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                justPrice.setText("BDT" + String.valueOf(totalP));

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Messages");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");

                Optional<ButtonType> option = alert.showAndWait();
                //String totalC = String.valueOf(totalP);*/
                if (option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(insertCustomerData);

                    prepare.setString(1, firstName);
                    prepare.setString(2, lastName);
                    prepare.setString(3, phoneNumber);
                    prepare.setString(4, location);
                    prepare.setString(5, location1);
                    prepare.setString(6, busId);
                    prepare.setString(7, type);
                    prepare.setString(8, ticketNum);
                    prepare.setString(9, String.valueOf(totalP));
                    prepare.setString(10, String.valueOf(bookingTicket_date.getValue()));
                    prepare.setString(11, Status);

                    prepare.executeUpdate();

                    String sqlEditStatus = "UPDATE lastbus SET status ='Not Available' WHERE bus_id ='" + busId + "'";
                    statement = connect.createStatement();
                    statement.executeUpdate(sqlEditStatus);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Messages");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Booked!!");
                    alert.showAndWait();

                    //reset();
                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    int id1 = 0;

    public void customerTicketBook1() {

        String insertCustomerData = "INSERT INTO  ticketcustomer(fname,lname,phoneno,tfrom,tto,busid,type,seatnum,price,date)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        connect = DatabaseConnect.connectDb();

        try {
            String firstName = bookingTicket_firstName.getText();
            String lastName = bookingTicket_lastName.getText();
            String phoneNumber = bookingTicket_phoneNum.getText();
            String date = String.valueOf(bookingTicket_date.getValue());
            String busId = (String) bookingTicket_busId1.getSelectionModel().getSelectedItem();
            String location = (String) bookingTicket_busLocation.getSelectionModel().getSelectedItem();
            String location1 = (String) bookingTicket_busLocation1.getSelectionModel().getSelectedItem();
            String type = (String) bookingTicket_Ac.getSelectionModel().getSelectedItem();
            String ticketNum = (String) bookingTicket_ticketNum.getSelectionModel().getSelectedItem();

            Alert alert;

            if (busId == null || location == null || location1 == null
                    || type == null || ticketNum == null || date.isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Messages");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();

            } else {

                String totalPrice = "SELECT price FROM lastbus WHERE frombus = '"
                        + location + "'";

                try {

                    connect = DatabaseConnect.connectDb();

                    prepare = connect.prepareStatement(totalPrice);
                    result = prepare.executeQuery();

                    if (result.next()) {
                        priceData = result.getDouble("price");
                    }

                    if (type == "Ac") {
                        totalP = (priceData + 100);
                    } else if (type == "Non-Ac") {
                        totalP = priceData;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                justPrice.setText("BDT" + String.valueOf(totalP));

                String id = "SELECT bus_id FROM lastbus WHERE frombus = '"
                        + location + "WHERE tobus ='" + location1 + "'";

                try {
                    connect = DatabaseConnect.connectDb();

                    prepare = connect.prepareStatement(id);
                    result = prepare.executeQuery();
                    if (result.next()) {
                        id1 = result.getInt("bus_id");
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Confirmation Messages");
                        alert.setHeaderText(null);
                        alert.setContentText("There is no bus for your destination");
                    }
                    practice.setText(String.valueOf(id1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 
                 
                 
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Confirmation Messages");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");

                Optional<ButtonType> option = alert.showAndWait();
                //String totalC = String.valueOf(totalP);*/
                if (option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(insertCustomerData);

                    prepare.setString(1, firstName);
                    prepare.setString(2, lastName);
                    prepare.setString(3, phoneNumber);
                    prepare.setString(4, location);
                    prepare.setString(5, location1);
                    prepare.setString(6, String.valueOf(id1));
                    prepare.setString(7, type);
                    prepare.setString(8, ticketNum);
                    prepare.setString(9, String.valueOf(totalP));
                    prepare.setString(10, String.valueOf(bookingTicket_date.getValue()));

                    prepare.executeUpdate();

                    String sqlEditStatus = "UPDATE lastbus SET status ='Not Available' WHERE bus_id ='" + busId + "'";
                    statement = connect.createStatement();
                    statement.executeUpdate(sqlEditStatus);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Messages");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Booked!!");
                    alert.showAndWait();

                    //reset();
                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void bookingTicketReset() {

        bookingTicket_firstName.setText("");
        bookingTicket_lastName.setText("");
        //bookingTicket_gender.getSelectionModel().clearSelection();
        bookingTicket_phoneNum.setText("");
        bookingTicket_date.setValue(null);

    }

    public void profile() {

        String user = UserProfileData.getUserName();

        String sql = "SELECT * FROM reistration1 WHERE username = '"
                + user + "'";

        try {

            connect = DatabaseConnect.connectDb();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                bookingTicket_firstName.setText(result.getString("firstname"));
                bookingTicket_lastName.setText(result.getString("lastname"));
                bookingTicket_phoneNum.setText(result.getString("phone"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BackPage() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("TourPlaces.fxml"));
        primaryStage.setTitle("DivisionWiseOneDay");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void ClosePage() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        busIdList();

        busLocationList();

        typeAc();

        ticketNumList();

        // bookingTicketSelect();
        bookingTicketReset();

        busToList();

        profile();
    }
}
