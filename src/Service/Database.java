package Service;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
    private static Connection instance;
    private Database(){
        
    }
    public static Connection getInstance() throws ClassNotFoundException, SQLException{
        if(instance == null){
            Class.forName("com.mysql.jdbc.Driver");  
            instance = (Connection) DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/quanlyraucu?useUnicode=true&characterEncoding=utf-8",
                            "root",
                            "");  
        }
        return instance;
    }
    
    
}
