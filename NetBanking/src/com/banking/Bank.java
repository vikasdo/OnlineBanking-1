package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Bank {
	public static void main(String[] args) {

    try {
    	 Class.forName("com.mysql.cj.jdbc.Driver");
//       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
       String url ="jdbc:mysql://banksystem123.mysql.database.azure.com:3306/bank?useSSL=true&requireSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
       Connection myDbConn = DriverManager.getConnection(url, "Adminuser@banksystem123", "Vikas12345");
       String query = " insert into test (Id, Name)"+"values (?, ?)";

       // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = myDbConn.prepareStatement(query);
      preparedStmt.setString (1, "12");
      preparedStmt.setString (2, "Rubble");
	int count1 = preparedStmt.executeUpdate();
	System.out.println("Inserted " + count1 + " row");
       System.out.println("Connection successful");
    } catch (Exception e) {
       System.err.println(e);
    }
  }
//	class.forName("com.mysql.jdbc.Driver");
//	Connection conn = null;
//	conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root", "");
//	System.out.print("Database is connected !");
//	conn.close();
}
