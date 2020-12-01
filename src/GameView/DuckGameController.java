package GameView;

import GameViewModel.DuckGameViewModel;
import View.ViewHandler;
import ViewModel.Chapter4ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class DuckGameController {
	private Region root;
	private ViewHandler viewhandler;
	private DuckGameViewModel model;
	
    @FXML
    void StartGameOnAction(ActionEvent event) throws Exception {
    	viewhandler.startDuckGame();
    }
	
	public void init(ViewHandler viewHandler, DuckGameViewModel duckgameviewmodel, Region root) {
	this.viewhandler = viewHandler;
	this.model = duckgameviewmodel;
	this.root = root;
	
	
	
	}
	public void reset() {
		
	}
	public Region getRoot() {
		return root;
	}
}
