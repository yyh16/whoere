package Servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.UserData;
import Position.Distance;
import Position.GroupData;
import Position.PositionNode;

public class UserList extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		double long1 = 0;
		double lat1 = 0;
		GroupData gd=new GroupData();
		HttpSession session =req.getSession();
		String userid=""+session.getAttribute("id");
		PositionNode po=gd.getPosition(userid);
		if ((!userid.equals("null"))&&po!=null){
			long1 = Double.parseDouble(po.getLongitude());
			lat1=Double.parseDouble(po.getLatitude());
		}
			
		
		LinkedList<PositionNode> position=gd.getPosition();
		UserData userdata=new UserData();
		String name="";
		String sex="";
		String age="";
		String distance="";
		String headimg="";
		Distance dis=new Distance();
		int index=0;
		while(index<position.size()){
			String id=position.get(index).id;
			userdata.getMember(id);
			userdata.next();
			name=userdata.getName();
			sex=userdata.getSex();
			age=userdata.getAge();
			headimg=userdata.getHeadimg();
			if(long1!=0&&lat1!=0){
			double long2 =Double.parseDouble(position.get(index).getLongitude());
			double lat2 = Double.parseDouble(position.get(index).getLatitude());
			double temp=dis.GetDistance(long1, lat1, long2, lat2);
			if(temp>1){
				distance=""+temp;
				if(distance.length()>4)
					distance=distance.substring(0, 4);
				distance=distance+"km";
			}else{
				temp=temp*1000;
				distance=""+temp;
				if(distance.length()>4)
					distance=distance.substring(0, 4);
				distance=distance+"m";
			}
			}else
				distance="999+km";
			
			String message="<div class='aperson'><img class='userhead' src='"+headimg+
					"' /> <span class='username'>"+name+"</span><span class='usersex'>"+sex+
					"</span><span class='userage'>"+age+"岁</span><span class='userdistance'>"+distance+
					"</span></div>";
			resp.getWriter().write(message);
			
			index++;
		}
	}
}
