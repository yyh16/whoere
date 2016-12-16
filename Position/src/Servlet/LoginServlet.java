package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.UserPassword;
import Position.CheckMobile;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String flag=req.getParameter("id");
		String password=req.getParameter("password");
		
		UserPassword serve=new UserPassword();		
		int id=serve.getPassword(flag, password);
		
		HttpSession session=req.getSession();
		if(id<1){
			session.setAttribute("error", id);
			resp.sendRedirect("login.jsp");
		}else{
			String userAgent=req.getHeader("User-Agent");
			CheckMobile cm=new CheckMobile();
			boolean mobile=cm.check(userAgent);
			
			session.removeAttribute("error");
			session.setAttribute("id", id);
			if(mobile)
				resp.sendRedirect("mview.jsp");
			else
				resp.sendRedirect("view.jsp");
		}
		
	}
}
