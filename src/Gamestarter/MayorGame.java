package Gamestarter;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Utility.Score;
import View.ViewHandler;

public class MayorGame {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private ArrayList<ImageView> enemies = new ArrayList<ImageView>();
	private ArrayList<Node> barriers = new ArrayList<Node>();

	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private VBox vbox;
	private VBox box;
	private Text scoretext = new Text();

	private Node player;
	private Point2D playerVelocity = new Point2D(0, 0);
	private boolean canJump = true;

	private int levelWidth;
	private int amountofenemies;
	

	private boolean dialogEvent = false, running = true;

	private ViewHandler viewhandler;

	
	private int halfwidth = 640;
	private int width = 1280;
	private int height = 720;

	public void start(Stage primaryStage, ViewHandler viewhandler) throws Exception {
		this.viewhandler = viewhandler;

		initcontent();

		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Mayor Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.centerOnScreen();
		primaryStage.show();

		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if (running) {
					update();
					EnemyAI();
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

	private void initcontent() {
		
		levelWidth = LevelData.level1[0].length() * 32;
		Rectangle bg = new Rectangle(width, height);
		bg.setFill(new ImagePattern(new Image("/Images/Cartooncity.jpg")));
		for (int i = 0; i < LevelData.level1.length; i++) {
			String lastchar = "0";
			String line = LevelData.level1[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '0':

					if (lastchar.equals("1")) {
						Node barrier = createEntity(j * 32, (i - 1) * 32, 5, 50, Color.TRANSPARENT);
						barriers.add(barrier);
					}
					lastchar = "0";
					break;
				case '1':
					if (lastchar.equals("0")) {
						Node barrier = createEntity(j * 32, (i - 1) * 32, 5, 50, Color.TRANSPARENT);
						barriers.add(barrier);
					}
					Image stone = new Image("/Images/Stonetexture.jpg");
					Node platform = createImageEntity(j * 32, i * 32, 32, 32, stone);
					platforms.add(platform);
					lastchar = "1";
					break;
				case '2':
					Image image = new Image("/Images/robber2.png");
					ImageView robber = createImageEntity(j * 32, i * 32, 20, 35, image);
					enemies.add(robber);
					lastchar = "2";
					amountofenemies++;
					break;
				}
			}
		}
		
		Image image = new Image("/Images/tinyplayer-removebg-preview.png");
		player = createImageEntity(10, 50, 20, 20, image);
		updatescore(amountofenemies+"");
		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > halfwidth && offset < levelWidth - halfwidth) {
				gameRoot.setLayoutX(-(offset - halfwidth));
			}
		});

		appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
	}

	private void initMathProblem() {
		QuestionMaker quest = new QuestionMaker();
		Text question = new Text(quest.createQuestion());
		TextField fieldAnswer = new TextField();
		Text Answer = new Text(quest.getAnswer());
		Answer.setVisible(false);
		Button btnSubmit = new Button("Submit");
		btnSubmit.setOnAction(event -> {
			if (Answer.getText().equals(fieldAnswer.getText())) {
				dialogEvent = false;
				running = true;
				Answer.setVisible(true);
				gameRoot.getChildren().remove(box);
				amountofenemies--;
				scoretext.setText(amountofenemies+"");
				if(amountofenemies == 0) {
					endGame();
				}
			}
		});

		box = new VBox(10, question, fieldAnswer, btnSubmit, Answer);
		box.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
		box.setTranslateX(player.getTranslateX());
		box.setTranslateY(player.getTranslateY());
		gameRoot.getChildren().addAll(box);
	}

	private void update() {
		if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
			jumpPlayer();
		}
		if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
			player.setScaleX(1);
			movePlayerX(-5);
		}
		if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5) {
			movePlayerX(5);
			player.setScaleX(-1);
		}
		if (isPressed(KeyCode.P)) {
			initMainmenu();
			running = false;
		}

		if (playerVelocity.getY() < 5) {
			playerVelocity = playerVelocity.add(0, 1);
		}
		movePlayery((int) playerVelocity.getY());

		for (Node enemy : enemies) {
			if (player.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
				enemy.getProperties().put("alive", false);
				dialogEvent = true;
				running = false;
			}
		}

		for (Iterator<ImageView> it = enemies.iterator(); it.hasNext();) {
			Node enemy = it.next();
			if (!(Boolean) enemy.getProperties().get("alive")) {
				it.remove();
				gameRoot.getChildren().remove(enemy);
			}
		}

	}

	private void EnemyAI() {
		for (Node enemy : enemies) {
			for (Node barrier : barriers) {
				if (enemy.getAccessibleText().contains("Left")) {
					enemy.setTranslateX((enemy.getTranslateX() - 0.05));
					if (enemy.getBoundsInParent().intersects(barrier.getBoundsInParent())) {
						enemy.setAccessibleText("Right");
						enemy.setScaleX(-1);
					}
				} else {
					if (enemy.getAccessibleText().contains("Right")) {
						enemy.setTranslateX(enemy.getTranslateX() + 0.05);
						if (enemy.getBoundsInParent().intersects(barrier.getBoundsInParent())) {
							enemy.setAccessibleText("Left");
							enemy.setScaleX(1);
						}
					}
				}

			}

		}
	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private void jumpPlayer() {
		if (canJump) {
			playerVelocity = playerVelocity.add(0, -20);
			canJump = false;
		}
	}

	private void movePlayerX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (player.getTranslateX() + 40 == platform.getTranslateX()) {
							return;
						}
					} else {
						if (player.getTranslateX() == platform.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
		}

	}

	private void movePlayery(int value) {
		boolean movingDown = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (player.getTranslateY() + 20 == platform.getTranslateY()) {
							player.setTranslateY(player.getTranslateY() - 1);
							canJump = true;
							return;
						}
					} else {
						if (player.getTranslateY() == platform.getTranslateY() + 32) {
							return;
						}
					}
				}
			}
			player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	private Node createEntity(int x, int y, int w, int h, Color color) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(color);
		gameRoot.getChildren().add(entity);
		return entity;
	}

	private ImageView createImageEntity(int x, int y, int w, int h, Image image) {
		ImageView enemy = new ImageView();
		enemy.setFitHeight(h);
		enemy.setFitWidth(w);
		enemy.setImage(image);
		enemy.setTranslateX(x);
		enemy.setTranslateY(y);
		enemy.getProperties().put("alive", true);
		enemy.setAccessibleText("Right");
		gameRoot.getChildren().add(enemy);
		return enemy;
	}

	private void MainMenu() {
		viewhandler.openView("Chapter1");
	}

	private void endGame() {
		running = false;
		Score score = new Score(10,"Mayor game");
		score.setOnCloseRequest(event->{
			viewhandler.openView("Chapter1");
		});
		
	}
	private void updatescore(String score) {
		scoretext.setText(score);
		Font font = Font.font(32);
		scoretext.setFont(font);
		VBox scorebox = new VBox(10,scoretext);
		scorebox.setTranslateX(1230);
		scorebox.setTranslateY(0);
		uiRoot.getChildren().addAll(scorebox);
	}

}
