package Servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Position.GroupData;

public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		ServletContext context=session.getServletContext();
		GroupData groupdata=(GroupData)context.getAttribute("group");
		
		if(groupdata==null){
			groupdata=new GroupData();
			context.setAttribute("group", groupdata);
		}
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		String id=""+session.getAttribute("id");
		GroupData gd=new GroupData();
		while(true){
			int number=gd.getUpdatePosition();
			if(number>0){
				gd.deletePosition(id);
				gd.setUpdatePosition(number);
				break;
			}
		}
	}
}
