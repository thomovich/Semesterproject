package View;

import ViewModel.ChaptersViewModel;
import ViewModel.FrontPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class ChaptersController {
	private Region root;
	private ChaptersViewModel ViewModel;
	private ViewHandler viewhandler;

	public ChaptersController() {

	}

	@FXML
	void changeToChapter1(ActionEvent event) {
		viewhandler.openView("Chapter1");
	}

	@FXML
	void changeToChapter2(ActionEvent event) {
		viewhandler.openView("Chapter2");
	}

	@FXML
	void changeToChapter3(ActionEvent event) {
		viewhandler.openView("Chapter3");
	}

	@FXML
	void changeToChapter4(ActionEvent event) {
		viewhandler.openView("Chapter4");
	}

	@FXML
	void changeToChapter5(ActionEvent event) {
		viewhandler.openView("Chapter5");
	}

	@FXML
	void changeToFrontPage(ActionEvent event) {
		viewhandler.openView("Frontpage");
	}

	public void init(ViewHandler viewhandler, ChaptersViewModel ViewModel, Region root) {
		this.viewhandler = viewhandler;
		this.ViewModel = ViewModel;
		this.root = root;

	}

	public Region getRoot() {
		return root;
	}

	public void reset() {
	}

}
