
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dell
 */
public class UserData {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String gender;
    private String pass;
    private String conpass;
    private String image;

    
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getPass() {
        return pass;
    }

    public String getConpass() {
        return conpass;
    }

    public String getImage() {
        return image;
    }

    public UserData(String firstname, String lastname, String username, String email, String phone, String gender, String pass, String conpass, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.pass = pass;
        this.conpass = conpass;
        this.image = image;
    }
   
    
}
