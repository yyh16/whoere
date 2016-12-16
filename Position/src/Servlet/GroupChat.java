package Servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.UserData;
import Position.ChatNode;
import Position.Distance;
import Position.GroupData;
import Position.PositionNode;

public class GroupChat extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String text=req.getParameter("text");
		resp.setHeader("Content-Type", "text/html;charset=UTF-8");
		HttpSession session =req.getSession();
		String id =""+(Integer)session.getAttribute("id");
		
		
		GroupData groupdata=new GroupData(); 
		Integer last =(Integer) session.getAttribute("last");
		if(last==null)
			last=0;
		while(true){
			int number=groupdata.getOccupy();
			if(number>0){
				int now=groupdata.insert(id, text);
				groupdata.setOccupy(number);
				session.setAttribute("last", now);
				break;
			}
		}
		double long1 = 0;
		double lat1 = 0;
		GroupData gd=new GroupData();
		String userid=""+session.getAttribute("id");
		PositionNode po=gd.getPosition(userid);
		if ((!userid.equals("null"))&&po!=null){
			long1 = Double.parseDouble(po.getLongitude());
			lat1=Double.parseDouble(po.getLatitude());
		}
		Distance dis=new Distance();
		LinkedList<ChatNode> chat=groupdata.getChat(last);
		UserData user=new UserData();
		String distance="";
		int i=0;
		while(i<chat.size()){
			ChatNode temp=chat.get(i);
			String chatid=temp.id;
			text=temp.text;
			po=gd.getPosition(chatid);
			if(long1!=0&&lat1!=0&&po!=null){
				double long2 = Double.parseDouble(po.getLongitude());
				double lat2=Double.parseDouble(po.getLatitude());
				double juli=dis.GetDistance(long1, lat1, long2, lat2);
				if(juli>1){
					distance=""+juli;
					if(distance.length()>4)
						distance=distance.substring(0, 4);
					distance=distance+"km";
				}else{	
					juli=juli*1000;
					distance=""+juli;
					if(distance.length()>4)
						distance=distance.substring(0, 4);
					distance=distance+"m";
				}
			}else
				distance="999+km";
			
			user.getMember(chatid);
			user.next();
			String name=user.getName();
			java.util.Date nowdate = new java.util.Date();
			String time=nowdate.toLocaleString();
			if(chatid.equals(id))
				resp.getWriter().write("<p style='clear:both;float:left;'>"+name+" "+time+" "+distance+"<br/><span style='margin:0 0 0 25px;'>"+text+"</span></p>");
			else
				resp.getWriter().write("<span style='clear:both;float:right;'>"+name+" "+time+" "+distance+"</span><span style='margin:0 25px 0 0;clear:both;float:right;'>"+text+"</span><br/>");
			i=i+1;
		}
		user.close();
		chat=null;
	}
}
