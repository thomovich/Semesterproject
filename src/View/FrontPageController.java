package View;

import ViewModel.FrontPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button submit;
	
	@FXML
    private Button but1;

    @FXML
    private TextField textfield;

    @FXML
    private ImageView forsideimage;

    @FXML
    void changeToChapter2(ActionEvent event) {
    	viewhandler.openView("Chapter2");
    }



	public void init(ViewHandler viewhandler, FrontPageViewModel ViewModel, Region root) {
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
