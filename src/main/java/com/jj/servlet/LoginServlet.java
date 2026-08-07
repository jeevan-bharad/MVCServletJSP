package com.jj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jj.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name =req.getParameter("name");
		String pass =req.getParameter("password");
		
	
		
		
		
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter out = resp.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root","root");
			 ps = con.prepareStatement("select * from register where name=? and password=?");
			 ps.setString(1, name);
			 ps.setString(2, pass);
			
			 ResultSet rs = ps.executeQuery();
			 
			 if(rs.next())
			 {
					
					
					
					User user = new User();
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setGender(rs.getString("gender"));
					user.setCity(rs.getString("city"));
					
					HttpSession s = req.getSession();
					s.setAttribute("user", user);
					
				 
					System.out.println("You are login successfully ");
					
					out.print("<h1 style='color:green'>Welcome on profile page</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
					rd.include(req, resp);
					
					
				}else {
					System.out.println("Something went wron while login");
					out.print("<h1 style='color:red'>Credential not correct</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("/login.html");
					rd.include(req, resp);
				}
		} catch (SQLException e) {
			System.out.println("Something went wron while login");
			out.print("<h1 style='color:red'>Exception Occured</h1>"+e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.include(req, resp);
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
