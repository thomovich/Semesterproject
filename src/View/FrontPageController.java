package View;

import ViewModel.FrontPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

import View.ViewHandler;
import ViewModel.FrontPageViewModel;

public class FrontPageController {

	private Region root;
	private FrontPageViewModel ViewModel;
	private ViewHandler viewhandler;

	public FrontPageController() {

	}

	@FXML
	private ImageView forsideimage;
	@FXML
	private Label submit;
	@FXML
	private TextField textfield;

	@FXML
	void ChoosegameOnAction(ActionEvent event) {
		viewhandler.openView("Chapter1");
	}

	public void init(ViewHandler viewhandler, FrontPageViewModel ViewModel, Region root) {
		this.viewhandler = viewhandler;
		this.ViewModel = ViewModel;
		this.root = root;

	}

	public void changeToChapter2(ActionEvent event) throws IOException {

		try {
			FXMLLoader tableViewParent = new FXMLLoader(getClass().getResource("Chapter2.fxml"));
			Parent root = tableViewParent.load();
			Scene tableViewScene = new Scene(root);
			Chapter2Controller Ctc = tableViewParent.<Chapter2Controller>getController();

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(tableViewScene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Region getRoot() {
		return root;
	}

	public void reset() {
	}

}
