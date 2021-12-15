/*
 * author 	: Amit Patil
 */

package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
//import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.ClientModel;
import models.Issue;

public class ClientController implements Initializable  {
	public static int userid;
	
	@FXML private Button btnDescribe;
	@FXML private Button btnProblemList;
	@FXML private Button btnLogout;
	
	@FXML private Group groupDescribe;
	@FXML private TextField txtDesc;
	@FXML private Button btnSubmit;
	@FXML private Text txtErrorMsg;
	
	@FXML private Group groupProblemList;
	@FXML private TableView<Issue> tableList;
	@FXML private TableColumn<Issue, Integer> colID;
	@FXML private TableColumn<Issue, Integer> colUserID;
	@FXML private TableColumn<Issue, String> colDesc;
	@FXML private TableColumn<Issue, String> colDate;
	@FXML private TableColumn<Issue, String> colStatus;
	
	ClientModel model;
	
	public ClientController() {
		model = new ClientModel();
	}
	
	public static void setUserid(int user_id) {
		userid = user_id;
		System.out.println("Welcome id " + userid);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		groupDescribe.setVisible(true);
		groupProblemList.setVisible(false);
		txtErrorMsg.setText("");
		txtDesc.setText("");
	}
	
	//btnDescribe click event
    public void OnBtnDescribeClicked() {
    	groupDescribe.setVisible(true);
		groupProblemList.setVisible(false);
    }
    
    //btnProblemList click event
    public void OnBtnProblemListClicked() {
     	groupDescribe.setVisible(false);
     	groupProblemList.setVisible(true);
     	
     	colID.setCellValueFactory(new PropertyValueFactory<Issue,Integer>("id"));
    	colUserID.setCellValueFactory(new PropertyValueFactory<Issue,Integer>("userId"));
     	colDesc.setCellValueFactory(new PropertyValueFactory<Issue, String>("desc"));
     	colDate.setCellValueFactory(new PropertyValueFactory<Issue, String>("date"));
     	colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("status"));
     	
     	ArrayList<Issue> issueList = model.GetIssueList();
     	if(issueList == null)
     	{
     		System.out.println("Issue list is null");
     		return;
     	}
     	
     	if(issueList.size() == 0)
     	{
     		System.out.println("The size of the problem list is 0");
     		return;
     	}
     	tableList.getItems().setAll(issueList);
    }
    
    //btnLogout click event
    public void	OnBtnLogoutClicked() {
    	AnchorPane root = null;
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene 	= new Scene(root);
		Main.stage.setTitle("Login View");
		Main.stage.setScene(scene);
	}
    
    //btnSubmit click event
    public void OnBtnSubmitClicked() {
    	String desc = txtDesc.getText();
    	if (desc == null || desc.trim().equals("")) {
			  txtErrorMsg.setText("Please describe the problem");
			  return;
		}
		boolean isSucced = model.SubmitNewProblem(ClientController.userid, desc);
		System.out.println("IsSucceed "+isSucced);
		txtErrorMsg.setText(isSucced?"Submit succed":"Submit failed");
    }
    
}