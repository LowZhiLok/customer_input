package com.another.text;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class AnotherTesting
 */
public class AnotherTesting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnotherTesting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con = null;
			PreparedStatement stment = null;
//			ResultSet rs = null;
//			Statement st = null;
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/submission";
			String dbUsername = "root";
			String dbPassword = "root";
			
			con = DriverManager.getConnection(url, dbUsername, dbPassword);
//			st = con.createStatement();
//			rs = st.executeQuery("select * from postcode");
			
//			while(rs.next()) {
//				response.setContentType("text/html");
//				response.getWriter().println("<br>" + rs.getString("state") + ", " + rs.getString("postcode"));
//			}
			
			stment = con.prepareStatement("INSERT INTO customer (name, dob, address, postcode_id) VALUES(?,?,?,?)");
			
			String name = request.getParameter("name");
			String dob = request.getParameter("dob");
			String address = request.getParameter("address");
			String postcode = request.getParameter("postcode");
			
			stment.setString(1, name);
			stment.setString(2, dob);
			stment.setString(3, address);
			stment.setString(4, postcode);
			
			stment.executeUpdate();
			stment.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String state = request.getParameter("state");

		response.getWriter().println("<h1>This is from AnotherTesting:</h1>");

		response.getWriter().println("This is name: " +name + "<br>");
		response.getWriter().println("This is dob: " +dob + "<br>");
		response.getWriter().println("This is address: " +address + "<br>");
		response.getWriter().println("This is postcode: " +postcode + "<br>");
		response.getWriter().println("This is state: " +state);
	}

}
