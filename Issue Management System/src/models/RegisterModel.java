package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DBConnect;

public class RegisterModel extends DBConnect
{
	
	Statement stmt;
	PreparedStatement ppstmt;

	//Register Account
	public boolean registerAccount(String userName,String password)
	{
		try
		{
			String sql = "";
			sql = "INSERT INTO a_pat_users(UserName,Password)VALUES(?,?)";
			
			ppstmt = connect().prepareStatement(sql);
			ppstmt.setString(1,userName);
			ppstmt.setString(2,password);

			int rs = ppstmt.executeUpdate();
			if (rs>0)
			{
			    System.out.println("insert new data: "+userName+" succeed");
			} 
			return rs > 0;
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
			System.out.println("Error in registration, please try again"+se);
		}
		return false;
	}
}
