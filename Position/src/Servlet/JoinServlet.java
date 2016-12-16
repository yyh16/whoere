package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.UserJoin;

public class JoinServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String name=req.getParameter("name");
		String password=req.getParameter("pwd");
		String phone=req.getParameter("phone");
		String email=req.getParameter("email");
		String authcode=req.getParameter("authcode");
		
		HttpSession session=req.getSession();
		String result=""+session.getAttribute("result");
		if(!authcode.equals(result)){
			resp.setHeader("Content-Type", "text/html;charset=UTF-8");	
			resp.getWriter().write("验证码错误<br>");
			resp.getWriter().write("返回上一页<a href='register.jsp'>注册</a>");
		}else{
			
		UserJoin join=new UserJoin();
		while(true){
			int number=join.getDemo();
			if(number>0){
				join.insertMember(name, phone, email,password);
				join.setDemo(number);
				join.close();
				session.removeAttribute("error");
				break;
			}
		}
		resp.sendRedirect("login.jsp");
		}
	}
}
