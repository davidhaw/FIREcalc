package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;  

public class DBuser {

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
    
    public void insertEarnings(String name, double yearly, double tax) {  
        String sql = "INSERT INTO earnings(name, yearly, tax) VALUES(?,?,?)";  
   
        try{  
        	
            Connection conn = this.connect();  
            PreparedStatement setStatement = conn.prepareStatement(sql);  
            setStatement.setString(1, name);  
            setStatement.setDouble(2, yearly);  
            setStatement.setDouble(3, tax);  
            setStatement.executeUpdate();  
       
        } catch (SQLException e) {  
        
        	System.out.println(e.getMessage());  
        
        }  
    }  
	
}
