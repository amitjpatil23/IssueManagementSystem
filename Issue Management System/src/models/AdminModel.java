/*
 * author 	: Amit Patil
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import dao.DBConnect;
import controllers.ClientController;

public class AdminModel extends DBConnect {
	
	Statement stmt;
	PreparedStatement ppstmt;
	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<Issue> problemList = new ArrayList<Issue>();
	
	//get account data
	public ArrayList<User> GetAccountsData() 
	{
		ResultSet rs = null;
		try 
		{
			stmt 		= connect().createStatement();
			String sql 	= "SELECT * from a_pat_users";
			rs 			= stmt.executeQuery(sql);
			
			connect().close();
			
			userList.clear();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("UserName"));
				user.setPassword(rs.getString("Password"));
				user.setIsAdmin(rs.getBoolean("IsAdmin"));
				System.out.println("ID: "+user.getId()+" UserName: "+user.getUserName()+" Password: "+user.getPassword()+" IsAdmin: "+user.getIsAdmin());
				userList.add(user);
				System.out.println(userList);
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return userList;
	}
	
	//Update Account
	public boolean UpdateAccount(int userId,String userName,String password,Boolean isAdd,boolean isDelete,Boolean isAdmin)
//	public boolean UpdateAccount(String userName,String password,Boolean isAdd,boolean isDelete,Boolean isAdmin)
	{
		System.out.println("update account clicked---->");
		System.out.println(userName);
		System.out.println(password);
		System.out.println(isAdmin);
		try
		{
			
			String sql = "";
			if (isAdd) 
			{

				sql = "INSERT INTO a_pat_users(ID,UserName,Password,IsAdmin)VALUES(null,?,?,?)";
			}
			else if(isDelete) 
			{
//				sql = "DELETE FROM a_pat_users WHERE ID =? or UserName=?";
				sql = "DELETE FROM a_pat_users WHERE UserName=?";
			}
			else 
			{
				sql = "UPDATE a_pat_users set Password=?,IsAdmin=? where ID=? or UserName =?";
			}
			
			ppstmt = connect().prepareStatement(sql);
			
			if (isAdd)
			{
				ppstmt.setString(1,userName);
				ppstmt.setString(2,password);
				ppstmt.setBoolean(3, isAdmin);
			}
			else if(isDelete)
			{
//				ppstmt.setInt(1, userId);
//				ppstmt.setString(2, userName);
				ppstmt.setString(1, userName);
			}
			else 
			{
				ppstmt.setString(1, password);
				ppstmt.setBoolean(2, isAdmin);
				ppstmt.setInt(3, userId);
				ppstmt.setString(4, userName);
			}
			System.out.println("ppstmt value --->");
			System.out.println(ppstmt);
			int rs = ppstmt.executeUpdate();
			System.out.println("rs value --->");
			System.out.println(rs);
			if(isAdd && rs>0)
			{
			    System.out.println("insert new data: "+" userId: "+
			    					userId+" userName: "+
			    					userName+" isAdmin: "+
			    					isAdmin+" succeed");
			} 
			return rs > 0;
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return false;
	}
	
	//get issue list
	public ArrayList<Issue> GetIssueData() 
	{
		ResultSet rs = null;
		try 
		{
			stmt 		= connect().createStatement();
			String sql 	= "SELECT * from a_pat_problems";
			rs 			= stmt.executeQuery(sql);
			
			connect().close();
			
			problemList.clear();
			while(rs.next())
			{
				Issue issue = new Issue();
				issue.setId(rs.getInt("ID"));
				issue.setUserId(rs.getInt("UserID"));
				issue.setDesc(rs.getString("Description"));
				issue.setDate(rs.getString("Date"));
				issue.setStatus(rs.getString("Status"));
				//System.out.println("ID: "+problem.getId()+" UserID: "+problem.getUserId()+" Description: "+problem.getDesc()+" Date: "+problem.getDate());
				problemList.add(issue);
			}
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return problemList;
	}
		
	//Update Problem
	public boolean UpdateProblem(int id,int userId,String description,String status,Boolean isAdd,boolean isDelete)
	{
		System.out.println("userId: ===> "+ LoginModel.currentUserID);
		
		try
		{
			String sql = "";
			if (isAdd) 
			{
				sql = "INSERT INTO a_pat_problems(ID,UserID,Description,Date,Status)VALUES(null,?,?,?,?)";
			}
			else if(isDelete) 
			{
				sql = "DELETE FROM a_pat_problems WHERE ID =?";
			}
			else 
			{
				sql = "UPDATE a_pat_problems set Description=?, Status=? where ID=?";
			}
			
			ppstmt = connect().prepareStatement(sql);
			
			if (isAdd)
			{
				ppstmt.setInt(1,LoginModel.currentUserID);
				ppstmt.setString(2,description);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ppstmt.setString(3,df.format(new java.util.Date()));
				ppstmt.setString(4,status);
			}
			else if(isDelete)
			{
				ppstmt.setInt(1, id);
			}
			else 
			{
				ppstmt.setString(1, description);
				ppstmt.setString(2, status);				
				ppstmt.setInt(3, id);
			}
			
			int rs = ppstmt.executeUpdate();
			if(isAdd && rs>0)
			{
				if(rs>0)
				{
				    System.out.println("insert new data userId: "+
				    					ClientController.userid+" description: "+
				    					description+" succeed");
				} 
			}
			return rs > 0;
		} 
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		return false;
	}
}