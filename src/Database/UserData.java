package Database;

import java.sql.SQLException;

public class UserData extends Database{
	
	private String id=null;
	private String name=null;
	private String sex=null;
	private String age=null;
	private String phone=null;
	private String email=null;
	private String headimg=null;
	
	public UserData(){
		super();
	}
	
	public String getAge() {
		return age;
	}
	public String getEmail() {
		return email;
	}
	public String getSex() {
		return sex;
	}
	public String getPhone() {
		return phone;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getHeadimg() {
		return headimg;
	}
	
	public void getMember(String id){
		try {
			rs=sql.executeQuery("select * from Member where id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean next(){
		boolean havenext=false;
		try {
			if(rs.next()){
				id=rs.getString(1);
				name=rs.getString(2);
				sex=rs.getString(3);
				age=rs.getString(4);
				phone=rs.getString(5);
				email=rs.getString(6);
				headimg=rs.getString(7);
				
				havenext=true;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return havenext;
	}
	
	public String getPassword(String id){
		String password="";
		try {
			rs=sql.executeQuery("select passwords from Passwords where id="+id);
			if(rs.next()){
				password=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}
	
}
