package View;

import GameViewModel.SpaceGameViewModel;
import ViewModel.Chapter5ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;



public class Chapter5Controller {
	private ViewHandler viewhandler;
	private Region root;
	private Chapter5ViewModel space;



	    @FXML
	    private TextField information;
	    

	    @FXML
	    void frontPage(ActionEvent event) {
	    	viewhandler.startFrontpage();
	    }

	    @FXML
	    void startGame(ActionEvent event) {
	    	String info = information.getText();
	    	viewhandler.startSpaceGame(info);
	    }

	
    public void init(ViewHandler viewHandler, Chapter5ViewModel space, Region root) {
    	this.viewhandler = viewHandler;
    	this.root = root;
    	this.space = space;
    }

	public void reset() {
		
	}

	public Region getRoot() {
		return root;
	}
	
	
}
