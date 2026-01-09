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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author My Lenovo
 */
public class BookHotel3Controller implements Initializable {

    @FXML
    private Button back;
    @FXML
    private Button close;
    
   
    

    @FXML
    private TableColumn<customerData, String> customers_checkedIn;

    @FXML
    private TableColumn<customerData, String> customers_checkedOut;

    @FXML
    private TableColumn<customerData, String> customers_customerNumber;

    @FXML
    private TableColumn<customerData, String> customers_firstName;

    @FXML
    private TableColumn<customerData, String> customers_lastName;

    @FXML
    private TableColumn<customerData, String> customers_phoneNumber;

    @FXML
    private TableColumn<customerData, String> customers_totalPayment;
    
      @FXML
    private TableView<customerData> customers_tableView;
      
      @FXML
    private TextField customers_Search;

    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
            

   public ObservableList<customerData> customerListData() {
        ObservableList<customerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * From customer1";
        connect = DatabaseConnect.connectDb();
        try {
            customerData custD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {

                custD = new customerData(result.getInt("customer_id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("phoneNumber"),
                result.getDouble("total"),result.getDate("checkIn"),result.getDate("checkOut"));

                listData.add(custD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<customerData> listCustomerData;
    public void customerShowData()
    {
        listCustomerData=customerListData();
        
        
        customers_customerNumber.setCellValueFactory(new PropertyValueFactory<>("customerNum"));
        customers_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        customers_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        customers_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customers_totalPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_checkedIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        customers_checkedOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        
        customers_tableView.setItems(listCustomerData);
      
        
    }
    
    public void customerSearch()
    {
        FilteredList<customerData> filter = new FilteredList<>(listCustomerData, e -> true);
        customers_Search.textProperty().addListener((Observable, oldValue, newValue) -> {
            
            filter.setPredicate(predicateCustomer ->{
                if (newValue == null && newValue.isEmpty()) {
                    return true;
                }

                String serachKey = newValue.toLowerCase();
                
                 if (predicateCustomer.getCustomerNum().toString().contains(serachKey)) {
                    return true;
                } else if (predicateCustomer.getFirstName().toLowerCase().contains(serachKey)) {
                    return true;
                } else if (predicateCustomer.getLastName().toString().contains(serachKey)) {
                    return true;
                } else if (predicateCustomer.getPhoneNumber().toLowerCase().contains(serachKey)) {
                    return true;
                    } else if (predicateCustomer.getTotal().toString().contains(serachKey)) {
                    return true;
                    } else if (predicateCustomer.getCheckIn().toString().contains(serachKey)) {
                    return true;
                     } else if (predicateCustomer.getCheckOut().toString().contains(serachKey)) {
                    return true;
                     }

                     else return false;
            
            });
            
        });
        SortedList<customerData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(customers_tableView.comparatorProperty());
        customers_tableView.setItems(sortList);
    }

    
    public void Back() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("BookHotel2.fxml"));
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
        customerShowData();
        customerSearch();
    }  
    
}
