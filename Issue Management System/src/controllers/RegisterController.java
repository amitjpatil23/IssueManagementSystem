/*
 * author 	: Amit Patil
 */
package controllers;

import java.io.IOException;
import java.net.URL;
//import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Text;
//import models.AdminModel;
//import models.ClientModel;
import models.RegisterModel;
//import models.Issue;
//import models.User;

public class RegisterController implements Initializable
{
	@FXML private TextField txtAccountUserName;
	@FXML private TextField txtAccountPassword;
	@FXML private Button btnRegisterAccount;
	@FXML private Button btnback;
	@FXML private Text txtRegisterMsg;
	@FXML private Group groupRegisterAccount;
	@FXML private Button btnBack;

	RegisterModel model;

	public RegisterController() 
	{
		model = new RegisterModel();
	}

	
	public void OnBtnRegisterAccountClicked() 
	{

		  String pwd = txtAccountPassword.getText();
		  String userName = txtAccountUserName.getText();
		
		  if (userName == null || userName.trim().equals("")) {
		  txtRegisterMsg.setText("Username Cannot be empty or spaces");
			  return;
		  }
		  if (pwd == null || pwd.trim().equals("")) {
			  txtRegisterMsg.setText("Password Cannot be empty or spaces");
			  return;
		  }
		  if (userName == null || userName.trim().equals("") && (pwd == null || pwd.trim().equals(""))) {
			  txtRegisterMsg.setText("User name / Password Cannot be empty or spaces");
			  return;
		  }
		
		boolean isSucceed 	= model.registerAccount(userName, pwd);
		System.out.println("IsSucced "+isSucceed);
		txtRegisterMsg.setText(isSucceed?"Registration Succeed":"Registration failed");		
		txtAccountUserName.setText("");
		txtAccountPassword.setText("");		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void OnBtnBackClicked() 
	{
		AnchorPane root = null;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Main.stage.setTitle("Login View");
		Main.stage.setScene(scene);
	}

}
