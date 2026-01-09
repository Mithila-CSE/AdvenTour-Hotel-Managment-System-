/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
import java.awt.Image;
import java.io.File;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class UserSignUpController implements Initializable {

    @FXML
    private Button Back1Button;

    @FXML
    private Button CreateButton;

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField register_conpass;

    @FXML
    private TextField register_email;

    @FXML
    private PasswordField register_pass;

    @FXML
    private TextField register_username;
    
    @FXML
    private TextField register_firstname;

    @FXML
    private ComboBox<?> register_gender;

    @FXML
    private TextField register_lastname;

    @FXML
    private TextField register_phone;
    
    @FXML
    private TextArea register_image;
    
    
    private Image image;
    
    private FileChooser filechooser;
    private File file;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    

    @FXML
    private void Hudai() {
        try {
            Stage stage = (Stage) Back1Button.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
            primaryStage.setTitle("Welcome");
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(UserSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Register() {
        String addData = "INSERT INTO reistration1 (firstname,lastname,username,email,phone,gender,pass,conpass,image) VALUES(?,?,?,?,?,?,?,?,?)";

        connect = DatabaseConnect.connectDb();

        try {

            Alert alert;

            if (register_firstname.getText().isEmpty()
                    || register_lastname.getText().isEmpty()
                    || register_username.getText().isEmpty()
                    ||register_email.getText().isEmpty()
                    ||register_phone.getText().isEmpty()
                    ||register_gender.getSelectionModel().getSelectedItem() == null
                    || register_conpass.getText().isEmpty()
                    || register_pass.getText().isEmpty()
                    || register_conpass.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All The Fields");
                alert.showAndWait();
            } else {

                String check = "SELECT username FROM reistration1 WHERE username = '"
                        + register_username.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                /*String check1 = "SELECT bus_id FROM bus WHERE bus_id = '"
                        + register_email.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check1);*/
                if (result.next()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Bus ID: " + register_username.getText() + " was already exist!");
                    alert.showAndWait();

                } else {
                    if (register_pass.getText().equals(register_conpass.getText())) {

                        prepare = connect.prepareStatement(addData);
                        prepare.setString(1, register_firstname.getText());
                        prepare.setString(2, register_lastname.getText());
                        prepare.setString(3, register_username.getText());
                        prepare.setString(4, register_email.getText());
                        prepare.setString(5, register_phone.getText());
                        prepare.setString(6,(String) register_gender.getSelectionModel().getSelectedItem());
                        prepare.setString(7, register_pass.getText());
                        prepare.setString(8, register_conpass.getText());
                        prepare.setString(9, register_image.getText());
                        

                        prepare.executeUpdate();
                        
                        Alert ab = new Alert(Alert.AlertType.CONFIRMATION);
                        ab.setContentText("Account Created successfully");
                        Optional<ButtonType> result = ab.showAndWait();
                        ButtonType button = result.orElse(ButtonType.CANCEL);
                        if (button == ButtonType.OK) {
                            Stage stage = (Stage) CreateButton.getScene().getWindow();
                            stage.close();
                            Stage primaryStage = new Stage();
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("UserLogIn.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(UserSignUpController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            primaryStage.setTitle("User LogIn");
                            primaryStage.setScene(new Scene(root));
                            //primaryStage.initStyle(StageStyle.UNDECORATED);
                            primaryStage.show();
                        } else {
                            System.out.println("Canclled");
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password Doesn't Match");
                        alert.showAndWait();
                    }
                    //availableBShowBusData();
                    //availableBusReset();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String[] GenderList = {"Male", "Female","Others"};

    public void comboBoxGender() {

        List<String> listS = new ArrayList<>();

        for (String data : GenderList) {
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        register_gender.setItems(listStatus);

    }
    
    public void setAllValue() {
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
    }
    
    public void getImage() {
        file = filechooser.showOpenDialog(new Stage());
        register_image.setText(file.getAbsolutePath());
        register_image.setEditable(false);
    }
    
    public void close() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       comboBoxGender();
       setAllValue();
       filechooser.setInitialDirectory(new File("C:\\Users\\Dell\\Desktop"));
    }
}
