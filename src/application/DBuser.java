package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;  

public class DBuser {

    private Connection connect() {
    	
   	 String url = "jdbc:sqlite:src/application/infodb.db";  
        Connection conn = null;  
        
        try {  
        
        	conn = DriverManager.getConnection(url);  
        
        } catch (SQLException e) {  
        
        	System.out.println(e.getMessage());  
        
        }  
        return conn;  
        
   }
    
    public void insertEarnings(double amntNeeded, double saveYearly) {
        String sql = "INSERT INTO earning(amntNeeded, saveYearly) VALUES(?,?)";  
   
        try{ 
            Connection conn = this.connect();  
            PreparedStatement setStatement = conn.prepareStatement(sql);  
            setStatement.setDouble(1, amntNeeded);  
            setStatement.setDouble(2, saveYearly);  
            setStatement.executeUpdate();  
            conn.close();
        } catch (SQLException e) {  
        	System.out.println(e.getMessage());  
        }  
    }  
	
}
