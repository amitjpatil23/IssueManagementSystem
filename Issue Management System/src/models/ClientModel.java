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

public class ClientModel extends DBConnect {
	
	private Statement stmt;
	private PreparedStatement ppstmt;
	
	private ArrayList <Issue> issueList = new ArrayList<Issue>();
    
	public ArrayList <Issue> GetIssueList() 
	{
		ResultSet rs = null;
		try 
		{
			stmt = connect().createStatement();
			String sql = "SELECT * from a_pat_problems where UserId ="+LoginModel.currentUserID;
			System.out.println("sql %s" +sql);
			rs = stmt.executeQuery(sql);
			
			issueList.clear();
			while(rs.next())
			{
				Issue problem = new Issue();
				problem.setId(rs.getInt("ID"));
				problem.setUserId(rs.getInt("UserID"));
				problem.setDesc(rs.getString("Description"));
				problem.setDate(rs.getString("Date"));
				problem.setStatus(rs.getString("Status"));
				issueList.add(problem);
			}
			
			connect().close();
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		return issueList;
	}
	
	
	public boolean SubmitNewProblem(int userId,String description) 
	{
		try 
		{
			String sql = "INSERT INTO a_pat_problems(ID,UserID,Description,Date,Status)Values(null,?,?,?,?)";
		    ppstmt = connect().prepareStatement(sql);
		    
		    ppstmt.setInt(1,userId);
		    ppstmt.setString(2, description);
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ppstmt.setString(3,df.format(new java.util.Date()));
			ppstmt.setString(4, "New");
			
			int rs = ppstmt.executeUpdate();
			if(rs>0)
			{
			    System.out.println("insert new data: userId: "+
			    					ClientController.userid+" description: "+
			    					description+" succeed");
			} 
			return rs>0;
		} 
		catch (SQLException se) 
		{
			se.printStackTrace();
		}
		
		return false;
	}
}
