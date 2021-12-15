/*
 * author 	: Amit Patil
 */
package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Issue {
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty userId;
	private SimpleStringProperty desc;
	private SimpleStringProperty date;
	private SimpleStringProperty status;
	
	public Issue() 
	{
		id 		= new SimpleIntegerProperty();
		userId 	= new SimpleIntegerProperty();
		desc 	= new SimpleStringProperty();
		date 	= new SimpleStringProperty();
		status 	= new SimpleStringProperty();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	
	public void setUserId(int userId) {
		this.userId.set(userId);
	}
	
	public void setDesc(String desc) {
		this.desc.set(desc);
	}
	
	public void setDate(String date) {
		this.date.set(date);
	}
	
	public void setStatus(String status) {
		this.status.set(status);
	}
	
	public int getId() {
		return this.id.get();
	}
	
	public int getUserId() {
		return this.userId.get();
	}
	
	public String getDesc() {
		return this.desc.get();
	}
	
	public String getDate() {
		return this.date.get();
	}
	
	public String getStatus() {
		return this.status.get();
	}
}
