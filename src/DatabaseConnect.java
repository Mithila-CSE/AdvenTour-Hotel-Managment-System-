/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;
import java.sql.*;
/**
 *
 * @author Dell
 */
public class DatabaseConnect {
    
    public static Connection connectDb()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect =DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","Susmi2001");
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
