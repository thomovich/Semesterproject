package View;

import ViewModel.Chapter1ViewModel;
import ViewModel.Chapter2ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

import View.ViewHandler;
import ViewModel.FrontPageViewModel;

public class Chapter2Controller {
	private Region root;
	private Chapter2ViewModel viewModel;
	private ViewHandler viewhandler;

	public Chapter2Controller() {

	}

	@FXML
	private Label submit1;
	@FXML
	private Label submit2;
	@FXML
	private Label submit3;
	@FXML
	private TextField textfield1;
	@FXML
	private TextField textfield2;
	@FXML
	private TextField textfield3;

	@FXML
	private ImageView firstProblem;

	@FXML
	private ImageView secondproblem;

	@FXML
	private ProgressBar progressbar;

	public void init(ViewHandler viewHandler, Chapter2ViewModel chapter2ViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = chapter2ViewModel;
		this.root = root;

	}

	public void changeToFrontPage(ActionEvent event) throws IOException {
		viewhandler.openView("Frontpage");
			
	}
	
	public void reset() {
	}

	public Region getRoot() {
		return root;
	}

}
