package View;

import ViewModel.Chapter1ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Region;




public class Chapter1Controller {
	private Region root;
	private Chapter1ViewModel viewModel;
	private ViewHandler viewhandler;

	public Chapter1Controller() {
	}


	   @FXML
	    private ProgressBar progressbar;

	   	@FXML
	    private Button firstProblem;

	    @FXML
	    private Button secondproblem;

	    @FXML
	    void firstproblemonaction() {

	    }

	    @FXML
	    void secondproblemonaction() {

	    }

	public void init(ViewHandler viewHandler, Chapter1ViewModel chapter1ViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = chapter1ViewModel;
		this.root = root;
		secondproblem.setStyle("-fx-background-image: url('/Images/closed_door.jpg')");
		firstProblem.setStyle("-fx-background-image: url('/Images/closed_door.jpg')");
	}

	public void reset() {
	}

	public Region getRoot() {
		return root;
	}

}
