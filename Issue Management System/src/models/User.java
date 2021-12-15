/*
 * author 	: Amit Patil
 */
package models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	private SimpleIntegerProperty id;
	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleBooleanProperty isAdmin;
	
	public User() 
	{
	   id 		= new SimpleIntegerProperty();
	   userName = new SimpleStringProperty();
	   password = new SimpleStringProperty();
	   isAdmin  = new SimpleBooleanProperty();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public void setUserName(String userName)
	{
		this.userName.set(userName);
	}
	
	
	public void setPassword(String password)
	{
		this.password.set(password);
	}
	
	public void setIsAdmin(boolean isAdmin){
		this.isAdmin.set(isAdmin);
	}
	
	public int getId() {
		return this.id.get();
	}
	
	public String getPassword()
	{
		return this.password.get();
	}
	
	public String getUserName()
	{
		return this.userName.get();
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin.get();
	}
}
