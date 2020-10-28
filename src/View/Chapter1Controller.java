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
    private Button firsproblem;

    @FXML
    private Button secondproblem;

    @FXML
    void firstproblemonaction() {

    }

    //test
    @FXML
    void secondproblemonaction() {

    }
    
    @FXML
    void BacktoChaptersOnAction() {

    }

	public void init(ViewHandler viewHandler, Chapter1ViewModel chapter1ViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = chapter1ViewModel;
		this.root = root;
	}

	public void reset() {
	}

	public Region getRoot() {
		return root;
	}

}
