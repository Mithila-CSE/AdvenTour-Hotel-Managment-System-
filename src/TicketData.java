
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class TicketData {
    private String firstname;
    private String lastname;
    private String phonenum;
    private String from;
    private String to;
    private Integer budId;
    private String type;
    private Integer seat;
    private Double price;
    private Date date;
    private String status;

    
    

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Integer getBudId() {
        return budId;
    }

    public String getType() {
        return type;
    }

    public Integer getSeat() {
        return seat;
    }

    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
    
    public String getStatus() {
        return status;
    }

    public TicketData(String firstname, String lastname, String phonenum, String from, String to, Integer budId, String type, Integer seat, Double price, Date date,String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenum = phonenum;
        this.from = from;
        this.to = to;
        this.budId = budId;
        this.type = type;
        this.seat = seat;
        this.price = price;
        this.date = date;
        this.status=status;
    }
    
}
