
import java.io.File;
import javafx.scene.image.Image;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class PlaceData {
    private String divisionText;
    private String placeText;
    private String imageText;
    private String DesText;
    
    
    public String getDivisionText() {
        return divisionText;
    }

    public String getPlaceText() {
        return placeText;
    }

    public String getImageText() {
        return imageText;
    }


    public String getDesText() {
        return DesText;
    }

    
    
    
    
    public PlaceData(String divisionText,String placeText,String imageText,String DesText){
        this.divisionText = divisionText;
        this.placeText = placeText;
        this.imageText = imageText;
        this.DesText = DesText;
    }
}
