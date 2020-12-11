package View;


import ViewModel.Chapter3ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class Chapter3Controller {
	private Region root;
	private Chapter3ViewModel viewModel;
	private ViewHandler viewhandler;

	public Chapter3Controller() {

	}


    @FXML
    private Button submit1;

    @FXML
    void startGame(ActionEvent event) throws InterruptedException {
    	viewhandler.startMinus_Game();
    }
    
    @FXML
    void changeToFrontPage(ActionEvent event) {
    	viewhandler.startFrontpage();
    }



	public void init(ViewHandler viewHandler, Chapter3ViewModel chapter3ViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = chapter3ViewModel;
		this.root = root;

	}

	
	public void reset() {
	}

	public Region getRoot() {
		return root;
	}

}
