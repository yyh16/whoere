package Database;

import java.sql.SQLException;

public class UserJoin extends Database{
	private static int demo=1;
	public UserJoin(){
		super();
	}
	public static int getDemo() {
		demo--;
		return demo+1;
	}
	public static void setDemo(int demo) {
		UserJoin.demo = demo;
	}
	public boolean insertMember(String name,String phone,String email,String password){
		try {
			int number=1;
			rs=sql.executeQuery("select count(id) from Member");
			if(rs.next()){
				number=rs.getInt(1)+1;
			}
			sql.executeUpdate("insert into Member values("+number+",'"+name+"','男',20,"+phone+",'"+email+"','img/user/head/0.JPG')");
			sql.executeUpdate("insert into Passwords values("+number+",'"+password+"')");
		} catch (SQLException e) {
			return false;
		}
		
		return true;			
	}

}
