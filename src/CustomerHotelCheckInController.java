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
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class CustomerHotelCheckInController implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private TextField firstName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField lastName;
    @FXML
    private TextField emailAddress;
    @FXML
    private DatePicker checkIn_date;
    @FXML
    private DatePicker checkOut_date;

    @FXML
    private ComboBox<?> roomNumber;
    @FXML
    private TextField customerNumber;
    @FXML
    private ComboBox<?> roomType;

    @FXML
    private Label total;

    @FXML
    private Label totalDays;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void reset() {
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");
        roomType.getSelectionModel().clearSelection();
        roomNumber.getSelectionModel().clearSelection();
        totalDays.setText("----");
        total.setText("0.0BDT");
    }

    public void totalDays() {
        Alert alert;

        if (checkOut_date.getValue().isAfter(checkIn_date.getValue())) {
            getData.totalDays = checkOut_date.getValue().compareTo(checkIn_date.getValue());

        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid check-out date!!");
            alert.showAndWait();
        }
        // displayTotal();
        //checkOut_date.setValue(null);

    }
    private double totalP = 0;

    public void displayTotal() {

        totalDays();
        String totalD = String.valueOf(getData.totalDays);
        totalDays.setText(totalD);
        String selectItem = (String) roomNumber.getSelectionModel().getSelectedItem();
        String sql = "SELECT *FROM hotelroom WHERE roomNumber ='" + selectItem + "'";
        double priceData = 0;
        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                priceData = result.getDouble("price");
            }
            totalP = ((priceData) * getData.totalDays);

            System.out.println("Total Payment: " + totalP);
            System.out.println("PriceData: " + priceData);
            //total.setText("BDT"+String.valueOf(totalP));
            total.setText(String.valueOf(totalP) + " BDT");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerNumber() {
        String customerNum = "SELECT customer_id FROM customer1";
        connect = DatabaseConnect.connectDb();
        try {
            prepare = connect.prepareStatement(customerNum);
            result = prepare.executeQuery();

            while (result.next()) {
                getData.customerNum = result.getInt("customer_id");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayCustomerNumber() {
        customerNumber();

        customerNumber.setText(String.valueOf(getData.customerNum + 1));
    }

    public void roomTypeList() {
        String listType = "SELECT * FROM hotelroom WHERE status ='Available' ";
        connect = DatabaseConnect.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while (result.next()) {
                listData.add(result.getString("type"));

            }

            roomType.setItems(listData);

            roomNumberList();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void roomNumberList() {

        String item = (String) roomType.getSelectionModel().getSelectedItem();
        //String availableRoomNumber="SELECT * FROM hotelroom Where type'"+item+"'";
        String availableRoomNumber = "SELECT * FROM hotelroom Where status ='Available' ";

        connect = DatabaseConnect.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(availableRoomNumber);
            result = prepare.executeQuery();
            while (result.next()) {
                listData.add(result.getString("roomNumber"));

            }
            roomNumber.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void customerCheckIn() {
        String insertCustomerData = "INSERT INTO  customer1(customer_id,total,roomType,roomNumber,firstName,lastName,phoneNumber,email,checkIn,checkOut)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        connect = DatabaseConnect.connectDb();

        try {
            String customerNum = customerNumber.getText();
            String roomT = (String) roomType.getSelectionModel().getSelectedItem();
            String roomN = (String) roomNumber.getSelectionModel().getSelectedItem();
            String firstN = firstName.getText();
            String lastN = lastName.getText();
            String phoneNum = phoneNumber.getText();
            String email = emailAddress.getText();
            String checkInDate = String.valueOf(checkIn_date.getValue());
            String checkOutDate = String.valueOf(checkOut_date.getValue());

            Alert alert;
            /*  if 
                     (firstN == null || lastN == null || phoneNum==null ||  email == null|| 
                     checkInDate == null || checkOutDate == null )*/
            if (firstName.getText().isEmpty()
                    || lastName.getText().isEmpty()
                    || phoneNumber.getText().isEmpty()
                    || emailAddress.getText().isEmpty()
                    || checkIn_date.getValue() == null
                    || checkOut_date.getValue() == null
                    || roomType.getSelectionModel().getSelectedItem() == null
                    || roomNumber.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Messages");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Messages");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");

                Optional<ButtonType> option = alert.showAndWait();
                String totalC = String.valueOf(totalP);
                if (option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(insertCustomerData);

                    prepare.setString(1, customerNum);
                    prepare.setString(2, totalC);
                    prepare.setString(3, roomT);
                    prepare.setString(4, roomN);

                    prepare.setString(5, firstN);
                    prepare.setString(6, lastN);
                    prepare.setString(7, phoneNum);
                    prepare.setString(8, email);
                    prepare.setString(9, checkInDate);
                    prepare.setString(10, checkOutDate);
                    prepare.executeUpdate();

                    String date = String.valueOf(checkIn_date.getValue());
                    // String totalC=String.valueOf(totalP);
                    String customerN = customerNumber.getText();

                    String customerReceipt = "INSERT INTO customer_receipt(customer_num,total,date)" + "VALUES(?,?,?)";

                    prepare = connect.prepareStatement(customerReceipt);

                    prepare.setString(1, customerN);
                    prepare.setString(2, totalC);
                    prepare.setString(3, date);

                    prepare.execute();

                    String sqlEditStatus = "UPDATE hotelroom SET status ='Occupied' WHERE roomNumber ='" + roomN + "'";
                    statement = connect.createStatement();
                    statement.executeUpdate(sqlEditStatus);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Messages");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully checked-In!!");
                    alert.showAndWait();

                    reset();
                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
                firstName.setText(result.getString("firstname"));
                lastName.setText(result.getString("lastname"));
                emailAddress.setText(result.getString("email"));
                phoneNumber.setText(result.getString("phone"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Back() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        primaryStage.setTitle("AdminPage");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void Close() throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayCustomerNumber();
        roomTypeList();
        roomNumberList();
        profile();

    }
}
