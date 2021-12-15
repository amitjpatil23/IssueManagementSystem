/*
 * author 	: Amit Patil
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Text;
import models.AdminModel;
//import models.ClientModel;
import models.Issue;
import models.User;

public class AdminController implements Initializable 
{

	@FXML private Button btnViewAccounts;
	@FXML private Button btnUpdateAccount;
	@FXML private Button btnViewProblems;
	@FXML private Button btnUpdateProblem;
	@FXML private Button btnLogout;
	
	@FXML private Group groupViewAccounts;
	@FXML private TableView<User> tableViewAccount;
	@FXML private TableColumn<User, Integer> colAccountID;
	@FXML private TableColumn<User, String>  colAccountUserName;
	@FXML private TableColumn<User, String>  colAccountPassword;
	@FXML private TableColumn<User, Boolean> colAccountIsAdmin;
	
	@FXML private Group groupUpdateAccounts;
	@FXML private TextField txtAccountID;
	@FXML private TextField txtAccountUserName;
	@FXML private TextField txtAccountPassword;
	@FXML private CheckBox checkAccountIsAdmin;
	@FXML private CheckBox checkAccountIsAdd;
	@FXML private CheckBox checkAccountIsDelete;
	@FXML private Button btnAccountUpdate;
	
	@FXML private Group groupViewProblems;
	@FXML private TableView<Issue> tableViewProblem;
	@FXML private TableColumn<Issue, Integer> colProblemID;
	@FXML private TableColumn<Issue, Integer> colProblemUserID;
	@FXML private TableColumn<Issue, String>  colProblemDesc;
	@FXML private TableColumn<Issue, String>  colProblemDate;
	@FXML private TableColumn<Issue, String>  colStatus;
	
	@FXML private Group groupUpdateProblem;
	@FXML private TextField txtProblemID;
	@FXML private TextField txtProblemDesc;
	@FXML private TextField txtStatus;
	@FXML private CheckBox checkProblemIsAdd;
	@FXML private CheckBox checkProblemIsDelete;
	@FXML private Button btnProblemUpdate;
	
	@FXML private Text txtErrorMsg;
	@FXML private Text txtScreenType;
	
//	txtProblemDesc.setWrapText(true);

	AdminModel model;
	public AdminController() {
		model = new AdminModel();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(false);
		
		OnBtnViewAccountsClicked();
	}
	
	public void OnBtnViewAccountsClicked()
	{
		groupViewAccounts.setVisible(true);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(false);
		txtScreenType.setText("View Accounts");
		txtErrorMsg.setText("");
				
		ArrayList<User> userList = model.GetAccountsData();
		if (userList!=null && userList.size()>0) 
		{
	     	colAccountID.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
	     	colAccountUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
	     	colAccountPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
	     	colAccountIsAdmin.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isAdmin"));
	     	
	     	tableViewAccount.getItems().setAll(userList);
		}
	}
	
	public void OnBtnUpdateAccountClicked() 
	{
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(true);
		groupUpdateProblem.setVisible(false);
		txtScreenType.setText("Manage Accounts");
	}
	
	public void OnBtnAccountUpdateClicked() 
	{
//		String id 			= txtAccountID.getText();
//		id 					= id.isEmpty()?"0":id;
		String id 			= "0";
		String pwd 			= txtAccountPassword.getText();
		String userName 	= txtAccountUserName.getText();
		boolean isAdd 		= checkAccountIsAdd.isSelected();
		boolean isDelete 	= checkAccountIsDelete.isSelected();
		boolean isAdmin  	= checkAccountIsAdmin.isSelected();
		System.out.println("isAdd ===> value "+isAdd);
		System.out.println("isDelete ===> value "+isDelete);
		System.out.println("isAdmin ===> value "+isAdmin);
		boolean isSucceed 	= model.UpdateAccount(Integer.parseInt(id), userName, pwd, isAdd, isDelete, isAdmin);
//		boolean isSucceed 	= model.UpdateAccount(userName, pwd, isAdd, isDelete, isAdmin);
		txtScreenType.setText("Account Update");
		System.out.println("IsSucced "+isSucceed);
		txtErrorMsg.setText(isSucceed?"Update Succeed":"Update failed");
	}
	
	public void OnBtnUpdateProblemClicked() 
	{
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(false);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(true);
		txtScreenType.setText("Manage Issues");
	}
	
	public void OnBtnViewProblemsClicked() 
	{
		groupViewAccounts.setVisible(false);
		groupViewProblems.setVisible(true);
		groupUpdateAccounts.setVisible(false);
		groupUpdateProblem.setVisible(false);
		txtScreenType.setText("View Issues");
		txtErrorMsg.setText("");
		
		ArrayList<Issue> problemList = model.GetIssueData();
		if (problemList!=null && problemList.size()>0) 
		{
	     	colProblemID.setCellValueFactory(new PropertyValueFactory<Issue,Integer>("id"));
	     	colProblemUserID.setCellValueFactory(new PropertyValueFactory<Issue, Integer>("userId"));
	     	colProblemDesc.setCellValueFactory(new PropertyValueFactory<Issue, String>("desc"));
	     	colProblemDate.setCellValueFactory(new PropertyValueFactory<Issue, String>("date"));
	     	colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("status"));
	     	tableViewProblem.getItems().setAll(problemList);
		}
	}
	
	public void OnBtnProblemUpdateClicked() 
	{
		String id 			= txtProblemID.getText();
		id 					= id.isEmpty()?"1":id;
		String description 	= txtProblemDesc.getText();
		String status 		= txtStatus.getText();
		boolean isAdd 		= checkProblemIsAdd.isSelected();
		boolean isDelete 	= checkProblemIsDelete.isSelected();
		System.out.println("id ===> "+id);
		boolean isSucceed 	= model.UpdateProblem(Integer.parseInt(id),ClientController.userid, description,status,isAdd, isDelete);
		txtErrorMsg.setText(isSucceed?"Update Succeed":"Update failed");
	    System.out.println("IsSucceed "+isSucceed);
	}
	
	public void OnBtnLogoutClicked() 
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
