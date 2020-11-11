package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ViewModel.ViewModelFactory;
import GameView.*;
import Gamestarter.MayorGame;

public class ViewHandler {
	private Stage primaryStage;
	private Scene currentScene;
	private ViewModelFactory ViewModelFactory;
	private Chapter1Controller chapter1controller;
	private Chapter2Controller chapter2controller;
	private MayorGameController MayorGameController;
	private AdditionGameController AdditionGameController;

	public ViewHandler(ViewModelFactory viewModelFactory) {
		this.ViewModelFactory = viewModelFactory;
		this.currentScene = new Scene(new Region());

	}

	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		startFrontpage();
		currentScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}

	public void openView(String id) {
		Region root = null;
		switch (id) {
		case "Chapter1":
			root = loadChapter1("Chapter1.fxml");
			break;
		case "Chapter2":
			root = loadChapter2("Chapter2.fxml");
			break;
		case "Mayorgame": 
			root = loadMayorGame("../GameView/MayorGame.fxml");
			break;
		case "AdditionGame":
			root = loadAdditionGame("../GameView/AdditionGame.fxml");
		}
		currentScene.setRoot(root);
		String title = "";
		if (root.getUserData() != null) {
			title += root.getUserData();
		}
		primaryStage.setTitle(title);
		primaryStage.setScene(currentScene);
		primaryStage.setWidth(root.getPrefWidth());
		primaryStage.setHeight(root.getPrefHeight());
		primaryStage.show();
	}

	public void closeView() {
		primaryStage.close();
	}

	
	
	
	private Region loadChapter1(String fxmlFile) {
		Region root = null;
		if (chapter1controller == null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				chapter1controller = loader.getController();
				chapter1controller.init(this, ViewModelFactory.getChapter1ViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			chapter1controller.reset();
		}
		return chapter1controller.getRoot();
	}
	private Region loadChapter2(String fxmlFile) {
		Region root = null;
		if (chapter2controller == null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				chapter2controller = loader.getController();
				chapter2controller.init(this, ViewModelFactory.getChapter2ViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			chapter2controller.reset();
		}
		return chapter2controller.getRoot();
	}
	private Region loadMayorGame(String fxmlFile) {

		Region root = null;
		if ( MayorGameController== null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				MayorGameController = loader.getController();
				MayorGameController.init(this, ViewModelFactory.getMayorGameViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// reset window
			MayorGameController.reset();
		}
		return MayorGameController.getRoot();
	}
	private Region loadAdditionGame(String fxmlFile) {

		Region root = null;
		if ( AdditionGameController== null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				AdditionGameController = loader.getController();
				AdditionGameController.init(this, ViewModelFactory.getAdditionGameViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// reset window
			AdditionGameController.reset();
		}
		return AdditionGameController.getRoot();
	}
	
	
	public void startmayorgame() throws Exception {
		MayorGame mayor = new MayorGame();
		mayor.start(primaryStage,this);
	}
	
	public void startFrontpage() {
		FrontPage front = new FrontPage();
		front.start(primaryStage,this);
	}
}


