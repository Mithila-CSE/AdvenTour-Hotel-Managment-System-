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
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TotalCustomerController implements Initializable {

    
    @FXML
    private TableView<UserData> UserTable;
    @FXML
    private TableColumn<UserData, String> colfirstname;
    @FXML
    private TableColumn<UserData, String> collastname;
    @FXML
    private TableColumn<UserData, String> colusername;
    @FXML
    private TableColumn<UserData, String> colemail;
    @FXML
    private TableColumn<UserData, String> colphone;
    @FXML
    private TableColumn<UserData, String> colgender;
    @FXML
    private TableColumn<UserData, String> colpass;
    @FXML
    private TableColumn<UserData, String> colconpass;
    @FXML
    private TableColumn<UserData, String> colimage;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField searchBar;
    @FXML
    private TextField Username_delete;
    @FXML
    private Button BackButton;
    
    @FXML
    private Button closeButton;
    
    private Connection connect=null;
    private PreparedStatement prepare=null;
    private ResultSet result=null;
    private Statement statement;
    
    public ObservableList<UserData> refreshData() {
        ObservableList<UserData> UserListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM reistration1";
        connect = DatabaseConnect.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            UserData userD;
            while (result.next()) {
                userD = new UserData(result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("gender"),
                        result.getString("pass"),
                        result.getString("conpass"),
                        result.getString("image"));

                UserListData.add(userD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return UserListData;
    }
    
    private ObservableList<UserData> availableUserListData;
    public void ShowUserData(){
        
        
        availableUserListData = refreshData();
        
        colfirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        collastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colpass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        colconpass.setCellValueFactory(new PropertyValueFactory<>("conpass"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        UserTable.setItems(availableUserListData);
       // searchTable_From.setCellValueFactory(new PropertyValueFactory<>("from"));
    }
    
    public void availableUserDelete() {

        String deleteData = "DELETE FROM reistration1 WHERE username = '"
                + Username_delete.getText() + "'";

        connect = DatabaseConnect.connectDb();

        try {

            Alert alert;

            
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete UserName: " + Username_delete.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                   ShowUserData();
                   refreshData();

                } else {
                    return;
                }
            
//            NOW LETS PROCEED TO BOOKING TICKET : ) 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void availableSelectUserData() {
        UserData busD = UserTable.getSelectionModel().getSelectedItem();
        int num = UserTable.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        Username_delete.setText(busD.getUsername());
        
    }
    
    public void Back() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminControl.fxml"));
        primaryStage.setTitle("AdminPage");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

      public void Close() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowUserData();
    }    
    
}
