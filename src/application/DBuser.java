package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  

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
    
    public void insertData(double amntNeeded, double saveYearly) {
        String sql = "INSERT INTO dataF(amntNeeded, saveYearly) VALUES(?,?)";  
   
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
    
    @SuppressWarnings("rawtypes")
	public ArrayList getEarnings ()
    {
    	ArrayList<Object> dbAllInfo = new ArrayList<>();
    	//DB last info will have the last to things from dbAll info, which will be the amntNeeded and saveYearly from the last db save. This will prevent us having to delete old data which the user might want back
    	ArrayList<Object> dbLastInfo = new ArrayList<>();
    	
    	String getSql = "SELECT amntNeeded, saveYearly FROM dataF";
    	int placement = 0;
    	
    	try (Connection conn = this.connect();
    			Statement stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery(getSql)){
    		while (rs.next()) {
    			
    			dbAllInfo.add(rs.getDouble("amntNeeded"));
    			dbAllInfo.add(rs.getDouble("saveYearly"));
    			placement++;
    		}
    		
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    	
    	placement++;
    	dbLastInfo.add(dbAllInfo.get(placement));
    	placement++;
    	dbLastInfo.add(dbAllInfo.get(placement));
    	System.out.println(dbLastInfo);
    	return dbLastInfo;	
    }
	
}
