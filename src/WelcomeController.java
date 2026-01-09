/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class WelcomeController implements Initializable {

    @FXML
    private Button LogIn1Button;
    
    @FXML
    private Button SignUpButton;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private Button AdminButton;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void LogIn() throws IOException{
       Stage stage = (Stage)LogIn1Button.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("UserLogIn.fxml"));
       
       primaryStage.setTitle("UserLogIn");
       primaryStage.setScene(new Scene(root));
       //primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.show();
    }
    
    public void SignUp() throws IOException{
       Stage stage = (Stage)SignUpButton.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
       
       primaryStage.setTitle("CreatAccount");
       primaryStage.setScene(new Scene(root));
      // primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.show();
    }
    public void close() throws IOException{
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }
    
    public void Admin() throws IOException{
       Stage stage = (Stage)AdminButton.getScene().getWindow();
       stage.close();
       Stage primaryStage = new Stage();
       Parent root  = FXMLLoader.load(getClass().getResource("Admin.fxml"));
       
       primaryStage.setTitle("Admin");
       primaryStage.setScene(new Scene(root));
       //primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.show();
    }
}
