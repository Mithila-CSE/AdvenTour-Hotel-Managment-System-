
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class BusCustomerData {
    private Integer customer;
    private String firstName;
    private String lastName;
    //private String gender;
    private String phoneNum;
    private Integer busId;
    private String location;
    private String ac;
    private Integer seatNum;
    private Double price;
    private Date date;
    
    public BusCustomerData(Integer customer, String firstName, String lastName, String phoneNum, Integer busId, String location, String ac, Integer seatNum, Double price, Date date){
        this.customer = customer;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.gender = gender;
        this.phoneNum = phoneNum;
        this.busId = busId;
        this.location = location;
        this.ac = ac;
        this.seatNum = seatNum;
        this.price = price;
        this.date = date;
    }
    
    public Integer getCustomer(){
        return customer;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public Integer getBusId(){
        return busId;
    }
    public String getLocation(){
        return location;
    }
    public String getAc(){
        return ac;
    }
    public Integer getSeatNum(){
        return seatNum;
    }
    public Double getPrice(){
        return price;
    }
    public Date getDate(){
        return date;
    }
}
