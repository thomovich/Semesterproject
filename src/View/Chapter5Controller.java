package View;

import GameViewModel.SpaceGameViewModel;
import ViewModel.Chapter5ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;



public class Chapter5Controller {
	private ViewHandler viewhandler;
	private Region root;
	private Chapter5ViewModel space;
	
	
		@FXML
	    private Label label;

	    @FXML
	    private ComboBox<String> difficulties;
	    

	    @FXML
	    void frontPage(ActionEvent event) {
	    	viewhandler.startFrontpage();
	    }

	    @FXML
	    void startGame(ActionEvent event) {
	    if(difficulties.getValue() == null) {
	    	label.setText("choose a difficulty");
	    } else {
	    	String info = difficulties.getValue();
	    	viewhandler.startSpaceGame(info);
	    	label.setText("Space Invaders");
	    }
	    	
	    }

	
    public void init(ViewHandler viewHandler, Chapter5ViewModel space, Region root) {
    	this.viewhandler = viewHandler;
    	this.root = root;
    	this.space = space;
    	difficulties.getItems().addAll("low", "medium", "high");
    }

	public void reset() {
		
	}

	public Region getRoot() {
		return root;
	}
	
	
}
