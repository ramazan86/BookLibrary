package bookrental;

import com.mysql.jdbc.Connection;   
import java.sql.*;
//

public class Verbindung {
   public Connection con;
   
    public void start(){
        try{
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/movierental_new","root","");
               
               
        }catch(Exception e){
            System.out.println("Verbindung zur Datenbank fehlgeschlagen!!" + e);
        }
    }
    
    public Connection getVerbindung(){
    return con;
    }
    
}
  