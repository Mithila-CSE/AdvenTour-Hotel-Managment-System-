/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Date;
/**
 *
 * @author Dell
 */
public class BusData {
    private Integer busId;   
    private String from;
    private String to;
    private String status;
    //private Integer capacity;
    private Double price;
    private Date date;
    
    
    public Integer getBusId() {
        return busId;
    }
    
    public String getFrom() {
        return from;
    }
    
    public String getTo() {
        return to;
    }
   
    public String getStatus() {
        return status;
    }
    
    /*public Integer getCapacity() {
        return capacity;
    }*/
    
    public Double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
    
    public BusData(Integer busId,String from,String to,String status,Double price,Date date){
        this.busId = busId;
        this.from=from;
        this.to=to;
        this.status=status;
       // this.capacity = capacity;
        this.price=price;
        this.date=date;
    }
    
}
