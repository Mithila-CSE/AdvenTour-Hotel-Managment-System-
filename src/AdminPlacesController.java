/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AdminPlacesController implements Initializable {

    @FXML
    private Button DeleteButton;
    
    @FXML
    private Button addButton;
    
    @FXML
    private Button upButton;
    
    @FXML
    private Hyperlink imageButton;
    
    @FXML
    private Hyperlink TextButton;
    
    @FXML
    private TextArea Dtext;
    
    @FXML
    private TextField placeName;
    
    @FXML
    private ComboBox Division;

    @FXML
    private TextArea ImageText;
    
    @FXML
    private ImageView im;
    
    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<PlaceData, String> PLaceTable_Description;

    @FXML
    private TableColumn<PlaceData, String> PLaceTable_Division;

    @FXML
    private TableColumn<PlaceData, String> PLaceTable_Image;

    @FXML
    private TableColumn<PlaceData, String> PLaceTable_Name;

    @FXML
    private TableView<PlaceData> PlaceTable;
    
    @FXML
    private Button closeButton;


    private Image image;
    
    private FileChooser filechooser;
    private File file;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private FileInputStream fis;
    
    public void availablePlaceAdd(){
        
        String addData = "INSERT INTO places1 (Division,placename,image,description) VALUES(?,?,?,?)";
        
        connect = DatabaseConnect.connectDb();
        
        try{
            
            Alert alert;
            
            if( Division.getSelectionModel().getSelectedItem()==null
                ||placeName.getText().isEmpty()
               || ImageText.getText().isEmpty()
               || Dtext.getText().isEmpty()){
                
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All The Fields");
                alert.showAndWait();
            }
            else{
                                
                String check = "SELECT placename FROM places1 WHERE placename = '"
                        + placeName.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);
                
                if (result.next()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("PlaceName: " + placeName.getText() + " was already exist!");
                    alert.showAndWait();

                }else{
                    
                    prepare = connect.prepareStatement(addData);
                    prepare.setString(1, (String) Division.getSelectionModel().getSelectedItem());
                    prepare.setString(2, placeName.getText());
                    prepare.setString(3, ImageText.getText());
                    prepare.setString(4, Dtext.getText());
                    
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    availableBShowPlaceData();
                    availablePlaceReset();
                    
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    
    public void availablePlaceUpdate(){
        String updateData = "UPDATE places1 SET Division = '"
                + Division.getSelectionModel().getSelectedItem()
                + "', description = '"+ Dtext.getText()               
                + "', image = '" + ImageText.getText()
                + "' WHERE placename = '" + placeName.getText() + "'";

        connect = DatabaseConnect.connectDb();
        
        Alert alert;
        try{
            
            if( Division.getSelectionModel().getSelectedItem() == null
               ||placeName.getText().isEmpty()
               || ImageText.getText().isEmpty()
               || Dtext.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Select The Item First");
                alert.showAndWait();
                
            }else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Place Name: " + placeName.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableBShowPlaceData();
                    availablePlaceReset();
                    
                } else {
                    return;
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
      public void availablePlaceDelete(){
        
        String deleteData = "DELETE FROM places1 WHERE placename = '"
                +placeName.getText()+"'";
        
        connect = DatabaseConnect.connectDb();
        
        try{
            
            Alert alert;
            
            if( Division.getSelectionModel().getSelectedItem() == null
               ||placeName.getText().isEmpty()
               || ImageText.getText().isEmpty()
               || Dtext.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the item first");
                alert.showAndWait();

            } else {
                
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Place Name: " + placeName.getText() + "?");
                
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    availableBShowPlaceData();
                    availablePlaceReset();
                    
                }else{
                    return;
                }
            }

        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void availablePlaceReset() {

        Division.getSelectionModel().clearSelection();
        placeName.setText("");
        ImageText.setText("");        
        Dtext.setText("");

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
    
    public void getText() {
        try {
            file = filechooser.showOpenDialog(new Stage());
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                Dtext.appendText(s.nextLine() + "\n");
            }
            Dtext.setEditable(false);
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
    }

    public void getImage() {
        file = filechooser.showOpenDialog(new Stage());
        ImageText.setText(file.getAbsolutePath());
        ImageText.setEditable(false);
    }
    
    private String[] DivisionList = {"Dhaka", "Chittagong","Rajshahi","Khulna","Barishal","Sylhet","Mymensingh","Rangpur"};

    public void comboBoxDivision() {

        List<String> listS = new ArrayList<>();

        for (String data : DivisionList) {
            listS.add(data);
        }

        ObservableList listStatus = FXCollections.observableArrayList(listS);
        Division.setItems(listStatus);

    }
    
    public ObservableList<PlaceData> availablePPlaceData() {
        ObservableList<PlaceData> PlaceListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM places1";
        connect = DatabaseConnect.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            PlaceData placeD = null;
            while(result.next())
            {
                placeD = new PlaceData(result.getString("Division"),
                        result.getString("placename"),
                        result.getString("image"),
                        result.getString("description")); 
                
                PlaceListData.add(placeD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return PlaceListData;
    }
    
    private ObservableList<PlaceData> availableBPlaceListData;
    
    public void availableBShowPlaceData(){
        
        availableBPlaceListData = availablePPlaceData();
        
        PLaceTable_Division.setCellValueFactory(new PropertyValueFactory<>("divisionText"));
        PLaceTable_Name.setCellValueFactory(new PropertyValueFactory<>("placeText"));
        PLaceTable_Image.setCellValueFactory(new PropertyValueFactory<>("imageText"));
        PLaceTable_Description.setCellValueFactory(new PropertyValueFactory<>("DesText"));
        
        PlaceTable.setItems(availableBPlaceListData);
        
    }
    
    public void availablePlaceSelectData(){
        PlaceData placeD = PlaceTable.getSelectionModel().getSelectedItem();
        int num = PlaceTable.getSelectionModel().getSelectedIndex();
        
        if((num-1)<-1){
            return;
        }
        
       // PLaceTable_Division.setText(placeD.getDivisionText());
        placeName.setText(placeD.getPlaceText());
        ImageText.setText(placeD.getImageText());
        Dtext.setText(placeD.getDesText());
        
        
    }
    public void Back() throws IOException {
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminControl.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    public void close() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAllValue();
        filechooser.setInitialDirectory(new File("C:\\Users\\Dell\\Desktop"));
        comboBoxDivision();
        
        availableBShowPlaceData();
    }

}
