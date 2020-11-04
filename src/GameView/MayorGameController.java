package GameView;

import GameViewModel.MayorGameViewModel;
import View.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class MayorGameController {
	private Region root;
	private MayorGameViewModel viewModel;
	private ViewHandler viewhandler;

	public MayorGameController() {

	}
    @FXML
    private Button letsgo;

	@FXML
	void letsgoonaction() throws Exception {
		viewhandler.startmayorgame();
		
	}


	public void init(ViewHandler viewHandler, MayorGameViewModel mayorGameViewModel, Region root) {
		this.viewhandler = viewHandler;
		this.viewModel = mayorGameViewModel;
		this.root = root;

	}

	public void reset() {
	}

	public Region getRoot() {
		return root;
	}
}
