package GameView;

import GameViewModel.OldladyViewModel;
import View.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;



public class OldLadyController {
	private ViewHandler viewhandler;
	private Region root;
	private OldladyViewModel oldlady;

    @FXML
    void BacktoChapter1(ActionEvent event) {
    	viewhandler.openView("Chapter1");
    }

    @FXML
    void startGameOnAction(ActionEvent event) {
    	viewhandler.startOldLadyGame();

    }
    
    
    
    public void init(ViewHandler viewHandler, OldladyViewModel oldlady, Region root) {
    	this.viewhandler = viewHandler;
    	this.root = root;
    	this.oldlady = oldlady;
    }

	public void reset() {
		
	}

	public Region getRoot() {
		return root;
	}
}
