package Gamestarter;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import View.ViewHandler;

import javafx.animation.AnimationTimer;

import javafx.geometry.Insets;

import javafx.scene.Node;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.input.KeyCode;

import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundFill;

import javafx.scene.layout.CornerRadii;

import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.paint.ImagePattern;

import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;

import javafx.scene.text.Text;

import javafx.stage.Stage;

public class AdditionGame {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<ImageView> trees = new ArrayList<ImageView>();
	private ArrayList<ImageView> goldcoins = new ArrayList<ImageView>();
	private ArrayList<ImageView> rocks = new ArrayList<ImageView>();
	private Node car;
	private VBox vbox;
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private boolean dialogEvent = false, running = true;
	private ViewHandler viewhandler;
	private String car1;
	private int levelWidth;
	Label text = new Label();
	private int halfwidth = 640;
	private int width = 1280;
	private int height = 720;
	private VBox box;
	private double  lastTimerCall = System.nanoTime();

// Start method with relevant methods fx. initcontent, which defines the background etc., spawner which spawns different nodes at random places....
	public void start(Stage primaryStage, ViewHandler viewhandler, String car1) throws Exception {
		this.viewhandler = viewhandler;
		this.car1 = car1;
		initcontent();
		spawner();
		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Addition Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.centerOnScreen();
		primaryStage.show();

// The animationTimer gets the nodes to move down, gets the player to move to avoid nodes etc.
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if (running) {
					Movement();
					moveYaxis();
					if(now>lastTimerCall+4_000_000_000l) {
						spawner();
						lastTimerCall=now;
					}
				}

				if (dialogEvent) {
					dialogEvent = false;
					keys.keySet().forEach(key -> keys.put(key, false));
					initMathProblem();

				}
			}
		};
		timer.start();

	}
// Getting the math problems from the AdditionQuestions class, for every time a player colludes with a node
	protected void initMathProblem() {
		AdditionQuestions problems = new AdditionQuestions();
		Text question = new Text(problems.createproblem());
		TextField fieldAnswer = new TextField();
		Text Answer = new Text(problems.getAnswer());
		Answer.setVisible(false);
		Button btnSubmit = new Button("Submit");
		btnSubmit.setOnAction(event -> {
			if (Answer.getText().equals(fieldAnswer.getText())) {
				Answer.setVisible(true);
				dialogEvent = false;
				running = true;
				appRoot.getChildren().remove(box);
			}

		});

		box = new VBox(10, question, fieldAnswer, btnSubmit, Answer);
		box.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
		box.setTranslateX(650);
		box.setTranslateY(450);
		appRoot.getChildren().addAll(box);

	}
//movement method which lets the player move around using right and left. Also the pause function.
//Also checking if the player is colluding with different nodes.
	private void Movement() {

		if (isPressed(KeyCode.RIGHT) && car.getTranslateX() + 40 <= 1240) {
			moveXaxis(7);
		}

		if (isPressed(KeyCode.LEFT) && car.getTranslateX() >= 5) {
			moveXaxis(-7);
		}

		if (isPressed(KeyCode.P)) {
			initMainmenu();
			running = false;

		}

		// Checker kollision med tr�er
		for (Node trees : trees) {
			if (car.getBoundsInParent().intersects(trees.getBoundsInParent())) {
				trees.getProperties().put("alive", false);
				dialogEvent = true;
				running = false;
			}
			if (trees.getTranslateY() > 640) {
				trees.getProperties().put("alive", false);
			}
		}

		for (Iterator<ImageView> it = trees.iterator(); it.hasNext();) {
			Node trees = it.next();
			if (!(Boolean) trees.getProperties().get("alive")) {
				it.remove();
				gameRoot.getChildren().remove(trees);

			}

		}

		for (Node rocks : rocks) {
			if (car.getBoundsInParent().intersects(rocks.getBoundsInParent())) {
				rocks.getProperties().put("alive", false);
				dialogEvent = true;
				running = false;
			}
			if (rocks.getTranslateY() > 640) {
				rocks.getProperties().put("alive", false);
			}
		}

		for (Iterator<ImageView> it = rocks.iterator(); it.hasNext();) {
			Node rocks = it.next();
			if (!(Boolean) rocks.getProperties().get("alive")) {
				it.remove();
				gameRoot.getChildren().remove(rocks);
			}
		}

		for (Node goldcoins : goldcoins) {
			if (car.getBoundsInParent().intersects(goldcoins.getBoundsInParent())) {
				goldcoins.getProperties().put("alive", false);
				dialogEvent = true;
				running = false;
			}
			if (goldcoins.getTranslateY() > 640) {
				goldcoins.getProperties().put("alive", false);
			}
		}

		for (Iterator<ImageView> it = goldcoins.iterator(); it.hasNext();) {
			Node goldcoins = it.next();
			if (!(Boolean) goldcoins.getProperties().get("alive")) {
				it.remove();
				gameRoot.getChildren().remove(goldcoins);

			}

		}
	}
// Letting the car move around the landscape
	private void moveXaxis(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			car.setTranslateX(car.getTranslateX() + (movingRight ? 1 : -1));

		}
	}
// Gets all the nodes to move down with different speed
	private void moveYaxis() {
		for (Node trees : trees) {
			trees.setTranslateY(trees.getTranslateY() + Math.random() * 2);

		}
		for (Node rocks : rocks) {
			rocks.setTranslateY(rocks.getTranslateY() + Math.random() * 2);
		}
		for (Node goldcoins : goldcoins) {
			goldcoins.setTranslateY(goldcoins.getTranslateY() + Math.random() * 3);
		}
	}
// tells the system if any key is pressed
	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private void MainMenu() {
		viewhandler.openView("Chapter4");
	}
//Open up a box, which let the user go back to main menu or resume game (pause button)
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
		appRoot.getChildren().addAll(vbox);

	}
//Defining the ImageView
	private ImageView createImageEntity(int x, int y, int w, int h, Image image) {
		ImageView trees = new ImageView();
		trees.setFitHeight(h);
		trees.setFitWidth(w);
		trees.setImage(image);
		trees.setTranslateX(x);
		trees.setTranslateY(y);
		trees.getProperties().put("alive", true);
		trees.setAccessibleText("Right");
		gameRoot.getChildren().add(trees);
		return trees;

	}
// A spawner of both Trees, Rocks and goldcoins. 
// Together with the animationTimer, this spawns all three with different locations.
	public void spawner() {
		for (int i = 0; i < map.level1.length; i++) {
			String line = map.level1[i];
			for (int j = 0; j < line.length(); j++) {
				int random = (int) (Math.random() * 4);
				if (random == 1) {
					Image Trees = new Image("/Images/tree.png");
					ImageView Tree = createImageEntity(j * 32, i * 32, 32, 32, Trees);
					trees.add(Tree);
				} else if (random == 2) {
					Image Goldcoin = new Image("/Images/goldcoin.png");
					ImageView coin = createImageEntity(j * 32, i * 32, 20, 35, Goldcoin);
					goldcoins.add(coin);
				} else if (random == 3) {
					Image Rock = new Image("/Images/rock.png");
					ImageView rock = createImageEntity(j * 32, i * 32, 20, 35, Rock);
					rocks.add(rock);
				} else {

				}
			}
		}
	}
// Defining the size of the game, background etc.
	private void initcontent() {
		levelWidth = map.level1[0].length() * 32;
		Rectangle background = new Rectangle(width, height);
		background.setFill(new ImagePattern(new Image("/Images/landscape.png")));

		Image image = new Image(car1);
		car = createImageEntity(0, 600, 40, 40, image);
		car.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > halfwidth && offset < levelWidth - halfwidth) {
				gameRoot.setLayoutX(-(offset - halfwidth));
			}
		});

		appRoot.getChildren().addAll(background, gameRoot, uiRoot);

	}

}