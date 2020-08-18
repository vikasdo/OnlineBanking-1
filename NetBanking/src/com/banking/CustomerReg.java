package com.banking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.xml.internal.ws.message.EmptyMessageImpl;
import com.banking.Database.DbOperation;
import com.banking.Model.AccountModel;
import com.banking.Model.CustomerModel;
import com.banking.Email.*;
@WebServlet("/Register")
public class CustomerReg extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		System.out.println("called");

		String accno = request.getParameter("accno");
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
//		System.out.println(accno);
//		System.out.println(uname);
//		System.out.println(pass);

		CustomerModel cm = new CustomerModel();
		cm.setAccount_number(accno);
		cm.setUsername(uname);
		cm.setPassword(pass);
		cm.setIs_verified(0);
//		city = request.getParameter("city");
		boolean output;
//		request.setAttribute("Account_details", am);
		DbOperation dbOperation = new DbOperation();
		
		try {
			output = dbOperation.customerSignup(cm);
			System.out.println("insertok");
			sendEmail email =new sendEmail();
			email.sendSimpleMessage(uname, "Verification", "You have been under verification process we will let you know after yours approved");
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
            System.out.println("okd");

			e.printStackTrace();
		}
}
}
