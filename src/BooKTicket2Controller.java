/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
//import com.mysql.cj.xdevapi.Statement;
import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
public class BooKTicket2Controller implements Initializable {

    @FXML
    private TextField availableBus_BusId;
    @FXML
    private TextField availableBus_ToField;
    @FXML
    private TextField availableBus_CapacityField;
    @FXML
    private TextField availableBus_FromField;
    @FXML
    private ComboBox<?> availableBus_Status;
    @FXML
    private TextField availableBus_PriceField;
    @FXML
    private DatePicker availableBus_datePicker;
    @FXML
    private Button availableBus_addButton;
    @FXML
    private Button availableBus_updateButton;
    @FXML
    private Button availableBus_deleteButton;
    @FXML
    private Button availableBus_resetButton;
    @FXML
    private TableView<BusData> searchTable_TableView;
    @FXML
    private TableColumn<String, BusData> searchTable_BusId;

    @FXML
    private TableColumn<String, BusData> searchTable_Date;

    @FXML
    private TableColumn<String, BusData> searchTable_From;

    @FXML
    private TableColumn<String, BusData> searchTable_Price;

    @FXML
    private TableColumn<String, BusData> searchTable_To;

    @FXML
    private TableColumn<String, BusData> searchTable_capacity;

    @FXML
    private TableColumn<String, BusData> searchTable_status;

    @FXML
    private TextField Bus_search;
    @FXML
    private Button availableBus_BackButton;
    @FXML
    private Button availableBus_closeButton;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void availableBusAdd() {

        String addData = "INSERT INTO lastbus (bus_id,frombus,tobus,status,price,date) VALUES(?,?,?,?,?,?)";

        connect = DatabaseConnect.connectDb();

        try {

            Alert alert;

            if (availableBus_BusId.getText().isEmpty()
                    || availableBus_FromField.getText().isEmpty()
                    || availableBus_Status.getSelectionModel().getSelectedItem() == null
                    || availableBus_PriceField.getText().isEmpty()
                    || availableBus_datePicker.getValue() == null) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {


                String check = "SELECT bus_id FROM lastbus WHERE bus_id = '"
                        + availableBus_BusId.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Bus ID: " + availableBus_BusId.getText() + " was already exist!");
                    alert.showAndWait();

                } else {

                    prepare = connect.prepareStatement(addData);
                    prepare.setString(1, availableBus_BusId.getText());
                    prepare.setString(2, availableBus_FromField.getText());
                    prepare.setString(3, availableBus_ToField.getText());
                    prepare.setString(4, (String) availableBus_Status.getSelectionModel().getSelectedItem());
                    prepare.setString(5, availableBus_PriceField.getText());
                    prepare.setString(6, String.valueOf(availableBus_datePicker.getValue()));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();


                    availableBShowBusData();
                    availableBusReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableBusUpdate() {
        String updateData = "UPDATE lastbus SET frombus = '"
                + availableBus_FromField.getText()
                + "', tobus = '" + availableBus_ToField.getText()
                + "', status = '" + availableBus_Status.getSelectionModel().getSelectedItem()
                + "', price = '" + availableBus_PriceField.getText()
                + "', date = '" + availableBus_datePicker.getValue()
                + "' WHERE bus_id = '" + availableBus_BusId.getText() + "'";

        connect = DatabaseConnect.connectDb();

        Alert alert;
        try {

            if (availableBus_FromField.getText().isEmpty()
                    || availableBus_ToField.getText().isEmpty()
                    || availableBus_BusId.getText().isEmpty()
                    || availableBus_Status.getSelectionModel().getSelectedItem() == null
                    || availableBus_PriceField.getText().isEmpty()
                    || availableBus_datePicker.getValue() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Select The Item First");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Bus ID: " + availableBus_BusId.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableBShowBusData();
                    availableBusReset();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableBusDelete() {

        String deleteData = "DELETE FROM lastbus WHERE bus_id = '"
                + availableBus_BusId.getText() + "'";

        connect = DatabaseConnect.connectDb();

        try {

            Alert alert;

            if (availableBus_FromField.getText().isEmpty()
                    || availableBus_ToField.getText().isEmpty()
                    || availableBus_BusId.getText().isEmpty()
                    || availableBus_Status.getSelectionModel().getSelectedItem() == null
                    || availableBus_PriceField.getText().isEmpty()
                    || availableBus_datePicker.getValue() == null) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Bus ID: " + availableBus_BusId.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableBShowBusData();
                    availableBusReset();

                } else {
                    return;
                }
            }
//            NOW LETS PROCEED TO BOOKING TICKET : ) 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableBusReset() {

        availableBus_FromField.setText("");
        availableBus_ToField.setText("");
        availableBus_BusId.setText("");
        availableBus_Status.getSelectionModel().clearSelection();
        availableBus_CapacityField.setText("");
        availableBus_PriceField.setText("");
        availableBus_datePicker.setValue(null);

    }

    private String[] statusList = {"Available", "Not Available"};

    public void comboBoxStatus() {

        List<String> listS = new ArrayList<>();

        for (String data : statusList) {
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        availableBus_Status.setItems(listStatus);

    }

    public ObservableList<BusData> availableBusData() {
        ObservableList<BusData> busListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM lastbus";
        connect = DatabaseConnect.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            BusData busD;
            while (result.next()) {
                busD = new BusData(result.getInt("bus_id"),
                        result.getString("frombus"),
                        result.getString("tobus"),
                        result.getString("status"),
                        result.getDouble("price"),
                        result.getDate("date"));

                busListData.add(busD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return busListData;
    }

    private ObservableList<BusData> availableBBusListData;

    public void availableBShowBusData() {

        availableBBusListData = availableBusData();

        searchTable_From.setCellValueFactory(new PropertyValueFactory<>("from"));
        searchTable_To.setCellValueFactory(new PropertyValueFactory<>("to"));
        searchTable_BusId.setCellValueFactory(new PropertyValueFactory<>("busId"));
        searchTable_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        //searchTable_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        searchTable_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        searchTable_Date.setCellValueFactory(new PropertyValueFactory<>("date"));

        searchTable_TableView.setItems(availableBBusListData);

    }

    public void availableBSelectBusData() {
        BusData busD = searchTable_TableView.getSelectionModel().getSelectedItem();
        int num = searchTable_TableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableBus_BusId.setText(String.valueOf(busD.getBusId()));
        availableBus_FromField.setText(busD.getFrom());
        availableBus_ToField.setText(busD.getTo());
       // availableBus_CapacityField.setText(String.valueOf(busD.getCapacity()));
        availableBus_PriceField.setText(String.valueOf(busD.getPrice()));
        availableBus_datePicker.setValue(LocalDate.parse(String.valueOf(busD.getDate())));
    }

    public void availableSearch() {

        FilteredList<BusData> filter = new FilteredList<>(availableBBusListData, e -> true);

        Bus_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateBusData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                if (predicateBusData.getBusId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getFrom().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getTo().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBusData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<BusData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(searchTable_TableView.comparatorProperty());
        searchTable_TableView.setItems(sortList);
    }

    @FXML
    private void Back(ActionEvent event) {
        Stage stage = (Stage) availableBus_BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Ticket_Manage.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Ticket_ManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void Close() throws IOException {
        Stage stage = (Stage) availableBus_closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBoxStatus();
        availableBShowBusData();
    }
}
