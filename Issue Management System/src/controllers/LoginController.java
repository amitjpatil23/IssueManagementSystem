/*
 * author 	: Amit Patil
 */
package controllers;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.LoginModel;

public class LoginController {

	  @FXML private TextField txtUserName;
	  @FXML private TextField txtPassword;
	  @FXML private Button btnSubmit;
	  @FXML private Button btnRegister;
	  @FXML private Text txtErrorMsg;
	  AnchorPane root;
	  
	  LoginModel model;
	  public LoginController() {
		  model = new LoginModel();
	  }
	  
	  public void checkCredentials(String username, String password) 
	  {
			Boolean isValid = model.getCredentials(username, password);
			if (!isValid) {
				txtErrorMsg.setText("User does not exist!");
				return;
			}
			try {
				
				if (model.isAdmin() && isValid) {
//					AnchorPane root;
					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
					Main.stage.setTitle("Admin View");

				} else {
					// If user is customer, inflate customer view

					root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
	
					int user_id = model.GetId();  
					ClientController.setUserid(user_id);
					Main.stage.setTitle("Client View");
				}

				Scene scene = new Scene(root);
				Main.stage.setScene(scene);

			} catch (Exception e) {
				System.out.println("Error occured while inflating view: " + e);
			}

		}
	  
	  public void OnBtnSubmitClicked() 
	  { 
		  
		  txtErrorMsg.setText("");	  
		  
		  String username = this.txtUserName.getText();
		  String password = this.txtPassword.getText();

		  if (username == null || username.trim().equals("")) {
			  txtErrorMsg.setText("Username Cannot be empty or spaces");
			  return;
		  }
		  if (password == null || password.trim().equals("")) {
			  txtErrorMsg.setText("Password Cannot be empty or spaces");
			  return;
		  }
		  if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			  txtErrorMsg.setText("User name / Password Cannot be empty or spaces");
			  return;
		  }
		  
		  checkCredentials(username, password);
	  }
	  
	  
	  public void OnRegisterClicked() 
	  { 
		  System.out.println("Register Clicked");
		  try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/RegisterView.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Register View");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error loading registration screen " + e);
		}
		  
	  }
	  
}
