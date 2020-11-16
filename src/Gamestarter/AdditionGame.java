package Gamestarter;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import View.ViewHandler;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdditionGame {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private Node car;
	private VBox vbox;
	private Pane appRoot = new Pane();
	private boolean dialogEvent = false, running = true;
	private ViewHandler viewhandler;
	private int levelWidth;
	Label text = new Label();

	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int halfwidth = (int) (screenSize.getWidth() / 2);
	private int width = (int) (screenSize.getWidth());
	private int height = (int) (screenSize.getHeight());
	private Node label;

	public void start(Stage primaryStage, ViewHandler viewhandler) throws Exception {
		this.viewhandler = viewhandler;

		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Addition Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	private void Controlls() {
		if (isPressed(KeyCode.RIGHT) && car.getTranslateX() + 40 <= levelWidth - 5) {
			Movement(-5);
			car.setScaleX(-1);
		}
		if (isPressed(KeyCode.LEFT) && car.getTranslateX() + 40 <= levelWidth - 5) {
			Movement(5);
			car.setScaleX(-1);
		}

		if (isPressed(KeyCode.P)) {
			initMainmenu();
			running = false;
		}
	}

	private void Movement(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (car.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (car.getTranslateX() + 40 == platform.getTranslateX()) {
							return;
						}
					} else {
						if (car.getTranslateX() == platform.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			car.setTranslateX(car.getTranslateX() + (movingRight ? 1 : -1));
		}

	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private void MainMenu() {
		viewhandler.openView("FrontPage");
	}

	private void initMainmenu() {

		Font font = Font.font(32);

		Button btnResume = new Button("Resume Game");
		btnResume.setOnAction(event -> {
			running = true;
			appRoot.getChildren().remove(vbox);

		});
		btnResume.setFont(font);

		Button mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(event -> MainMenu());
		mainMenu.setFont(font);
		vbox = new VBox(20, btnResume, mainMenu);
		vbox.setTranslateX(400);
		vbox.setTranslateY(400);
		// test
		appRoot.getChildren().addAll(vbox);
	}

	private void timer() {
		PauseTransition text = new PauseTransition(Duration.seconds(8));
		text.setOnFinished(event -> label.setVisible(false));
		text.play();
	}
}