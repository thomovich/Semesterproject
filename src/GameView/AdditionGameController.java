package GameView;

import GameViewModel.AdditionGameViewModel;
import View.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class AdditionGameController {

	private Region root;
	private AdditionGameViewModel viewModel;
	private ViewHandler viewhandler;
	
	@FXML
	private Button Frontpage;
	private Button CloseGame;
	@FXML
	void forsideonaction() throws Exception {
		viewhandler.startFrontpage();
	}
	@FXML
	void CloseGameonaction() throws Exception{
		viewhandler.closeView();
	}
	
	@FXML
	private Label score;
	
	public void init(ViewHandler viewHandler, AdditionGameViewModel AdditionGameViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = AdditionGameViewModel;
		this.root = root;

	}
	public void reset() {
		
		
	}
	public Region getRoot() {
		return root;
	}

}
