package workingTests;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;  
	
public class WorkingDB {

    private Connection connect() {
    	
    	
    	 String url = "jdbc:sqlite:infodb.db";  
         Connection conn = null;  
         try {  
             conn = DriverManager.getConnection(url);  
         } catch (SQLException e) {  
             System.out.println(e.getMessage());  
         }  
         return conn;  
    }
    

    public void insert(String name, int yearly, double tax) {  
        String sql = "INSERT INTO earnings(name, yearly, tax) VALUES(?,?,?)";  
   
        try{  
            Connection conn = this.connect();  
            PreparedStatement setStatement = conn.prepareStatement(sql);  
            setStatement.setString(1, name);  
            setStatement.setInt(2, yearly);  
            setStatement.setDouble(3, tax);  
            setStatement.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
   
    
    /**
     * @param args the command line arguments
     */
 /*  public static void main(String[] args) {
        WorkingDB app = new WorkingDB();  
        // insert three new rows  
        String Ename = "Job";
        app.insert(Ename, 300000000, 0.15);  
    } 
   */ 
}