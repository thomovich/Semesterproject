package View;

import ViewModel.Chapter1ViewModel;
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
import view.ChatWindowController;

import java.io.IOException;

import View.ViewHandler;
import ViewModel.FrontPageViewModel;

public class Chapter2Controller {
	private Region root;
	private Chapter1ViewModel viewModel;
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

	public void init(ViewHandler viewHandler, Chapter1ViewModel chapter1ViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = chapter1ViewModel;
		this.root = root;

	}
	ublic void changeToChapter2(ActionEvent event) throws IOException {

		try {
			FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
			Parent root = tableViewParent.load();
			Scene tableViewScene = new Scene(root);
			FrontPageController fpc = tableViewParent.<FrontPageController>getController();

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(tableViewScene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void reset() {
	}

	public Region getRoot() {
		return root;
	}

}
