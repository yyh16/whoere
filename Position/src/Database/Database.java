package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public Connection dbConn=null;
	public Statement sql=null;
	public ResultSet rs=null;
	
	public Database(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/club";
        String user = "root"; 
        String password = "123456";
		try{			
		    Class.forName(driver);
		    Connection dbConn  = DriverManager.getConnection(url, user, password);
		    sql = dbConn.createStatement();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(sql!=null){
				sql.close();
				sql=null;
			}
			if(dbConn!=null){
				dbConn.close();
				dbConn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
