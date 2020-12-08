package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import miniTennis.tennis;
import ViewModel.ViewModelFactory;

import java.awt.EventQueue;

import javax.swing.JFrame;

import GameView.*;
import Gamestarter.AdditionGame;
import Gamestarter.DuckGame;
import Gamestarter.MayorGame;
<<<<<<< Upstream, based on branch 'main' of https://github.com/thomovich/Semesterproject
import Space.SpaceInvaders;
=======
import Gamestarter.OldLadyGame;
>>>>>>> 74ad6a9 Updates

public class ViewHandler {
	private Stage primaryStage;
	private Scene currentScene;
	private ViewModelFactory ViewModelFactory;
	private Chapter1Controller chapter1controller;
	private Chapter2Controller chapter2controller;
	private MayorGameController MayorGameController;
	private Chapter4Controller Chapter4Controller;
	private DuckGameController duckgamecontroller;
	private OldLadyController oldladycontroller;
	private Chapter5Controller chapter5Controller;

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
		case "Chapter4":
			root = loadChapter4("Chapter4.fxml");
			break;
		case "duckgame":
			root = loadDuckGame("../GameView/DuckGame.fxml");
			break;
		case "oldlady":
			root = loadOldLadyGame("../GameView/Oldlady.fxml");
<<<<<<< Upstream, based on branch 'main' of https://github.com/thomovich/Semesterproject
		
		case"Chapter5":
			root = loadSpaceGame("Chapter5.fxml");
=======
			break;
>>>>>>> 74ad6a9 Updates
			
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
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	private Region loadOldLadyGame(String fxmlFile) {
		Region root = null;
		if (oldladycontroller == null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				oldladycontroller = loader.getController();
				oldladycontroller.init(this, ViewModelFactory.getOldLadyViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			oldladycontroller.reset();
		}
		return oldladycontroller.getRoot();
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
		if (MayorGameController == null) {
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

	private Region loadChapter4(String fxmlFile) {

		Region root = null;
		if (Chapter4Controller == null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				Chapter4Controller = loader.getController();
				Chapter4Controller.init(this, ViewModelFactory.getAdditionGameViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// reset window
			Chapter4Controller.reset();
		}
		return Chapter4Controller.getRoot();
	}



	
	private Region loadDuckGame(String fxmlFile) {

		Region root = null;
		if ( duckgamecontroller == null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				duckgamecontroller = loader.getController();
				duckgamecontroller.init(this, ViewModelFactory.getDuckGameViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// reset window
			duckgamecontroller.reset();
		}
		return duckgamecontroller.getRoot();
	}
	
	
	
	public void startmayorgame() throws Exception {
		MayorGame mayor = new MayorGame();
		mayor.start(primaryStage, this);
	}

	public void startFrontpage() {
		FrontPage front = new FrontPage();
		front.start(primaryStage, this);
	}

	public void startAdditiongame() throws Exception {
		AdditionGame addition = new AdditionGame();
		addition.start(primaryStage, this);

	}

	public void startMiniTennis() throws InterruptedException {
		tennis game = new tennis();
		JFrame frame = new JFrame();
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		
			
	}
}
	public void startDuckGame() throws Exception {
		DuckGame duckgame = new DuckGame();
		duckgame.start(primaryStage,this);
	}
	
	public void startSpaceGame(String info) {
		EventQueue.invokeLater(() -> {

            SpaceInvaders ex = new SpaceInvaders(info);
            ex.setVisible(true);
        });
	}
	
	public void startOldLadyGame() {
		OldLadyGame oldlady = new OldLadyGame();
		oldlady.Start(primaryStage, this);
	}
	
	private Region loadSpaceGame(String fxmlFile) {
		Region root = null;
		if (chapter5Controller == null) {
			// load from FXML
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(fxmlFile));
				root = loader.load();
				chapter5Controller = loader.getController();
				chapter5Controller.init(this, ViewModelFactory.getChapter5ViewModel(), root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			chapter5Controller.reset();
		}
		return chapter5Controller.getRoot();
	}
}
