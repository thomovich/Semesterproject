package View;

import ViewModel.Chapter4ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.layout.Region;

public class Chapter4Controller {
	private Region root;
	private Chapter4ViewModel viewModel;
	private ViewHandler viewhandler;

	public Chapter4Controller() {
	}

	@FXML
	void BacktoChaptersOnAction() {
		viewhandler.startFrontpage();

	}

	public void init(ViewHandler viewHandler, Chapter4ViewModel chapter4ViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = chapter4ViewModel;
		this.root = root;
	}

	public void reset() {
	}

	public Region getRoot() {
		return root;
	}

	@FXML
	void car1() throws Exception {
		viewhandler.startAdditiongame();
	}

	@FXML
	void car2() throws Exception {
		viewhandler.startAdditiongame();
	}
}