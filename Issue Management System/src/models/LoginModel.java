/*
 * author 	: Amit Patil
 */
package models;

//import java.awt.datatransfer.SystemFlavorMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.DBConnect;

public class LoginModel extends DBConnect {
	
	public User user;
	static int currentUserID;
	public LoginModel() {
		user = new User();
	}
	
	public int GetId(){
		if(user == null) 
		{
			return -1;	
		}		
		return user.getId();
	}
	
	public boolean isAdmin()
	{
		if (user==null) {
			return false;
		}
		return user.getIsAdmin();
	}

		
	public Boolean getCredentials(String username, String password)
	{   
        	String query = "SELECT * FROM a_pat_users WHERE UserName =? and Password =?";
        	System.out.println(query);
        	try(PreparedStatement stmt = connect().prepareStatement(query)) {
            	System.out.println("printing username and password");
            	System.out.println(username);
            	System.out.println(password);
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) 
                {
                	System.out.println(rs.getInt("ID"));
                	System.out.println(rs.getBoolean("IsAdmin"));
                	
                	user.setId(rs.getInt("ID"));
                	user.setIsAdmin(rs.getBoolean("IsAdmin"));
                	currentUserID = user.getId();
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
			return false;
    }
}
