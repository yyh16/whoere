package Database;

import java.sql.SQLException;

public class UserPassword extends Database{
	
	public UserPassword(){
		super();
	}
	
	public int getPassword(String flag,String userp){
		String password="";
		int id=0;
		UserData user=new UserData();
		boolean digital=true;

		for(int i=0;i<flag.length();i++){
			char a=flag.charAt(i);
			if(!(a>=48&&a<59)){
				digital=false;
				break;
			}
		}
		try {
			if(digital)
				rs=sql.executeQuery("select id from Member where id="+flag +" or phone="
					+flag+" or email='"+flag+"' or name='"+flag+"'");
			else
				rs=sql.executeQuery("select id from Member where email='"+flag+"' or name='"+flag+"'");
			while(rs.next()){
				password=user.getPassword(rs.getString(1));	
				if(password.equals(userp)){
					id=rs.getInt(1);
					break;
				}
				id=-1;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		user.close();
		return id;
	}
}
