package View;

import ViewModel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class Logincontroller {
	private Region root;
	private ViewHandler viewhandler;
	private LoginViewModel loginviewmodel;

	@FXML
	private ComboBox<String> Usernames = new ComboBox<String>();

	@FXML
	void Loginonaction() {
		//students();
	if(Usernames.getValue() == null) {
	System.out.println("Choose a user");
	} else {
		loginviewmodel.setUser(Usernames.getValue());
		viewhandler.startFrontpage();
	}
	
	}

	public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root) {
	this.viewhandler = viewHandler;
	this.loginviewmodel = loginViewModel;
	this.root = root;
	Usernames.setItems(loginviewmodel.getStudents());
	
	}

	public void reset() {
		
	}

	public Region getRoot() {
		// TODO Auto-generated method stub
		return root;
	}
}
