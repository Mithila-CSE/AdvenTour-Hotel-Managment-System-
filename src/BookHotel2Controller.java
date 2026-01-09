/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
import static com.sun.marlin.ByteArrayCache.check;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author My Lenovo
 */
public class BookHotel2Controller implements Initializable {

    @FXML
    private Button availableRooms_addBtn;

    @FXML
    private Button availableRooms_checkInBtn;

    @FXML
    private Button availableRooms_clearBtn;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_price;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomNumber;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_roomType;

    @FXML
    private TableColumn<roomData, String> availableRooms_col_status;

    @FXML
    private Button availableRooms_deleteBtn;

    @FXML
    private TextField availableRooms_price;

    @FXML
    private TextField availableRooms_roomNumber;

    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private TextField availableRooms_search;

    @FXML
    private ComboBox<?> availableRooms_status;

    @FXML
    private TableView<roomData> availableRooms_tableView;

    @FXML
    private Button availableRooms_updateBtn;

    @FXML
    private Button backButton;

    @FXML
    private Button close;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void availableRoomsSelectData() {
        roomData roomD = availableRooms_tableView.getSelectionModel().getSelectedItem();
        int num = availableRooms_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        availableRooms_roomNumber.setText(String.valueOf(roomD.getRoomNumber()));
        availableRooms_price.setText(String.valueOf(roomD.getPrice()));
    }

    public void availableRoomsSearch() {
        FilteredList<roomData> filter = new FilteredList<>(roomDataList, e -> true);
        availableRooms_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateRoomData -> {
                if (newValue == null && newValue.isEmpty()) {
                    return true;
                }

                String serachKey = newValue.toLowerCase();

                if (predicateRoomData.getRoomNumber().toString().contains(serachKey)) {
                    return true;
                } else if (predicateRoomData.getRoomType().toLowerCase().contains(serachKey)) {
                    return true;
                } else if (predicateRoomData.getPrice().toString().contains(serachKey)) {
                    return true;
                } else if (predicateRoomData.getStatus().toLowerCase().contains(serachKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<roomData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(availableRooms_tableView.comparatorProperty());
        availableRooms_tableView.setItems(sortList);

    }

    public void availableRoomsAdd() {
        String sql = "INSERT INTO hotelroom (roomNumber,type,status,price) VALUES(?,?,?,?)";

        connect = DatabaseConnect.connectDb();

        try {
            String roomNumber = availableRooms_roomNumber.getText();
            String type = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
            String status = (String) availableRooms_status.getSelectionModel().getSelectedItem();
            String price = availableRooms_price.getText();

            Alert alert;

            if (roomNumber == null || type == null || status == null || price == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Messages");
                alert.setHeaderText((null));
                alert.setContentText("Please Fill all blanks fields");
                alert.showAndWait();

            } else {

                String check = "SELECT roomNumber FROM hotelroom WHERE roomNumber = '"
                        + availableRooms_roomNumber.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Room Number: " + availableRooms_roomNumber.getText() + " was already exist!");
                    alert.showAndWait();

                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, roomNumber);
                    prepare.setString(2, type);
                    prepare.setString(3, status);
                    prepare.setString(4, price);

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Messages");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!!");

                    alert.showAndWait();

                    availableRoomsShowData();
                    availableRoomsClear();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<roomData> availableRoomsListData() {
        ObservableList<roomData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * From hotelroom";
        connect = DatabaseConnect.connectDb();
        try {
            roomData roomD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {

                roomD = new roomData(result.getInt("roomNumber"),
                        result.getString("type"),
                        result.getString("status"),
                        result.getDouble("price"));

                listData.add(roomD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<roomData> roomDataList;

    public void availableRoomsShowData() {

        roomDataList = availableRoomsListData();

        availableRooms_col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        availableRooms_col_roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        availableRooms_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        availableRooms_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        availableRooms_tableView.setItems(roomDataList);

    }

    public void availableRoomsUpdate() {
        String type1 = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String) availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_price.getText();
        String roomNum = availableRooms_roomNumber.getText();
        String sql = "UPDATE hotelroom SET type='" + type1 + "',status ='" + status1 + "',price ='" + price1 + "'WHERE roomNumber='" + roomNum + "'";
        try {
            Alert alert;
            if (type1 == null || status1 == null || price1 == null || roomNum == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Messages");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Messages");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!!");
                alert.showAndWait();

                availableRoomsShowData();
                availableRoomsClear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableRoomsDelete() {
        String type1 = (String) availableRooms_roomType.getSelectionModel().getSelectedItem();
        String status1 = (String) availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_price.getText();
        String roomNum = availableRooms_roomNumber.getText();

        String deleteData = "DELETE From hotelroom WHERE roomNumber='" + roomNum + "'";
        // String deleteData="DELETE hotelroom SET type='"+type1+"',status='"+status1+"',price='"+price1+"'WHERE roomNumber='"+roomNum+"'";

        connect = DatabaseConnect.connectDb();

        try {
            Alert alert;
            if (type1 == null || status1 == null || price1 == null || roomNum == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Messages");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Messages");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete roomnumber" + roomNum + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Messages");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!!");
                    alert.showAndWait();

                    availableRoomsClear();
                    availableRoomsShowData();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    

    public void availableRoomsClear() {
        availableRooms_roomNumber.setText("");
        availableRooms_roomType.getSelectionModel().clearSelection();
        availableRooms_status.getSelectionModel().clearSelection();
        availableRooms_price.setText("");

    }

    private String type[] = {"Single Room", "Double Room", "Tripple Room", "Quad Room", "King Room"};

    public void availableRoomsRoomType() {
        List<String> listData = new ArrayList<>();
        for (String data : type) {
            listData.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listData);
        availableRooms_roomType.setItems(list);

    }

    private String status[] = {"Available", "Not Available", "Occupied"};

    public void availableRoomsStatus() {
        List<String> listData = new ArrayList<>();
        for (String data : status) {
            listData.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listData);
        availableRooms_status.setItems(list);

    }

    public void Back() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HotelManage.fxml"));
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
        // TODO

        availableRoomsShowData();

        availableRoomsRoomType();

        availableRoomsStatus();

    }

}
