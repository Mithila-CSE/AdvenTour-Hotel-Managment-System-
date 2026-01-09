/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import Data.DatabaseConnect;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class TourPlacesController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> bookBarishal;

    @FXML
    private ComboBox<?> bookCtg;

    @FXML
    private ComboBox<?> bookDhaka;

    @FXML
    private ComboBox<?> bookKhulna;

    @FXML
    private ComboBox<?> bookMymen;

    @FXML
    private ComboBox<?> bookRaj;

    @FXML
    private ComboBox<?> bookRang;

    @FXML
    private ComboBox<?> bookSylhet;

    @FXML
    private Button closeButton;

    @FXML
    private Button hotel;

    @FXML
    private ImageView im;

    @FXML
    private Label text;

    @FXML
    private Button transport;

    private Image image1;

    private FileChooser filechooser;
    private File file;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void DhakaList() {
        String dhaka = "SELECT * FROM places1 WHERE Division = 'Dhaka'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(dhaka);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookDhaka.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ctgList() {
        String ctg = "SELECT * FROM places1 WHERE Division = 'Chittagong'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(ctg);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookCtg.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void RajList() {
        String raj = "SELECT * FROM places1 WHERE Division = 'Rajshahi'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(raj);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookRaj.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void KhulnaList() {
        String khulna = "SELECT * FROM places1 WHERE Division = 'Khulna'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(khulna);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookKhulna.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void BariList() {
        String bari = "SELECT * FROM places1 WHERE Division = 'Barishal'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(bari);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookBarishal.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SylhetList() {
        String syl = "SELECT * FROM places1 WHERE Division = 'Sylhet'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(syl);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookSylhet.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void RangList() {
        String rang = "SELECT * FROM places1 WHERE Division = 'Rangpur'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(rang);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookRang.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void MymenList() {
        String mymen = "SELECT * FROM places1 WHERE Division = 'Mymensingh'";

        connect = DatabaseConnect.connectDb();

        try {
            prepare = connect.prepareStatement(mymen);
            result = prepare.executeQuery();

            ObservableList listB = FXCollections.observableArrayList();

            while (result.next()) {

                listB.add(result.getString("placename"));
            }
            bookMymen.setItems(listB);

            //ticketNumList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String DesData;

    public void showImage() {

        String place = (String) bookDhaka.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData);
        }
    }

    private String DesData1;

    public void showImage1() {

        String place = (String) bookCtg.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData1 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData1);
        }
    }

    private String DesData2;

    public void showImage2() {

        String place = (String) bookRaj.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData2 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData2);
        }
    }

    private String DesData3;

    public void showImage3() {

        String place = (String) bookKhulna.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData3 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData3);
        }
    }

    private String DesData4;

    public void showImage4() {

        String place = (String) bookBarishal.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData4 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData4);
        }
    }

    private String DesData5;

    public void showImage5() {

        String place = (String) bookSylhet.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData5 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData5);
        }
    }

    private String DesData6;

    public void showImage6() {

        String place = (String) bookRang.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData6 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData6);
        }
    }

    private String DesData7;

    public void showImage7() {

        String place = (String) bookMymen.getSelectionModel().getSelectedItem();

        Alert alert;

        if (place == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String finalPlace = "SELECT * FROM places1 WHERE placename = ?";

            try {

                connect = DatabaseConnect.connectDb();

                prepare = connect.prepareStatement(finalPlace);
                prepare.setString(1, place);
                result = prepare.executeQuery();

                while (result.next()) {
                    //File file = new File()
                    file = new File(result.getString("image"));
                    image1 = new Image(file.toURI().toString());
                    im.setImage(image1);
                    DesData7 = result.getString("description");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rejuana");
            }

            text.setText(DesData7);
        }
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

    public void Transport() throws IOException {
        Stage stage = (Stage) transport.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CustomerTicket.fxml"));

        primaryStage.setTitle("CreatAccount");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void HotelPage() throws IOException {
        Stage stage = (Stage) hotel.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CustomerHotelCheckIn.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void Back() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));

        primaryStage.setTitle("CreatAccount");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void CloseFXML() throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAllValue();
        DhakaList();
        ctgList();
        RajList();
        KhulnaList();
        BariList();
        RangList();
        SylhetList();
        MymenList();
    }

}
