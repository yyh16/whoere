package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.UserData;
import Position.CheckMobile;
import Position.GroupData;
import Position.PositionNode;

public class RceiveLocation extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String longitude=req.getParameter("longitude");
		String latitude=req.getParameter("latitude");
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		
		HttpSession session =req.getSession();
		String id=""+session.getAttribute("id");
		
		GroupData gd=new GroupData();
		if(!id.equals("null")){
			UserData user= new UserData();
			user.getMember(id);
			user.next();
			String name=user.getName();
			user.close();
			
			while(true){
				int number=gd.getUpdatePosition();
				if(number>0){
					String userAgent=req.getHeader("User-Agent");
					CheckMobile cm=new CheckMobile();
					boolean mobile=cm.check(userAgent);
					
					gd.share(id, longitude, latitude, name,mobile);
					gd.setUpdatePosition(number);
					break;
				}
			}
		}
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		LinkedList<PositionNode> position=gd.getPosition();

		int i=0;
		while(i<position.size()){
			PositionNode temp=position.get(i);
			resp.getWriter().write(temp.getName()+"&"+temp.getLongitude()+"&"+temp.getLatitude()+"&");	
			i++;
		}
		gd=null;
	}
}
