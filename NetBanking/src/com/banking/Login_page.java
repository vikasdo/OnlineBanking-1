package com.banking;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.Database.db_con;
import com.banking.Model.CustomerModel;
@WebServlet("/login")
public class Login_page extends HttpServlet {

	
	String UserName, password;
	Connection conn;
	Statement stmt;
	CustomerModel cm = null;
	boolean pass_wrong = false;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserName = request.getParameter("UserName");
		password = request.getParameter("password");

		System.out.println(UserName);
		System.out.println(password);

		try {
			// Getting database connection
			db_con connect = new db_con();
			conn = connect.getConnection();

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(
					"select * from customer where username ='" + UserName + "'" + "and password='" + password + "'");

			if (!rs.isBeforeFirst()) {
				request.setAttribute("isPassOK", "No");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			} else {
				while (rs.next()) {
					pass_wrong = true;
					// Setting all variables to model class
					cm = new CustomerModel();
					cm.setUsername(UserName);
					cm.setAccount_number(rs.getString(4));
					
					
					ResultSet rs1 = stmt.executeQuery("select * from Bank_table where account_number ='" + cm.getAccount_number() + "'");

					while (rs1.next()) {
						cm.setAmount(rs1.getInt(3));
					}

					// Setting Session variable for current User
					HttpSession session = request.getSession();
					session.setAttribute("userDetails", cm);

					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


