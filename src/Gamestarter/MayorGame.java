package Gamestarter;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import View.ViewHandler;

public class MayorGame {
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private ArrayList<ImageView> coins = new ArrayList<ImageView>();

	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private VBox vbox;

	private Node player;
	private Point2D playerVelocity = new Point2D(0, 0);
	private boolean canJump = true;

	private int levelWidth;

	private boolean dialogEvent = false, running = true;

	private ViewHandler viewhandler;

	public void start(Stage primaryStage, ViewHandler viewhandler) throws Exception {
		this.viewhandler = viewhandler;

		initcontent();

		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Mayor Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(1080);
		primaryStage.setWidth(1920);
		primaryStage.centerOnScreen();
		primaryStage.show();

		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if (running) {
					update();
				}
				if(dialogEvent) {
					dialogEvent = false;
					keys.keySet().forEach(key -> keys.put(key, false));
					
					GameDialog dialog = new GameDialog();
					dialog.setOnCloseRequest(event -> {
						if(dialog.isCorrect()) {
							System.out.println("correct");
						} else {
							System.out.println("false");
						}
						
						running = true;
						dialog.close();
					});
					dialog.Open();
				}
			}
		};
		timer.start();
	}
	private void initMainmenu() {
		
		Font font = Font.font(32);
		
		Button btnResume = new Button("Resume Game");
		btnResume.setOnAction(event ->{
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
		Rectangle bg = new Rectangle(1920, 1080);
		bg.setFill(Color.ANTIQUEWHITE);
		levelWidth = LevelData.level1[0].length() * 60;
		for (int i = 0; i < LevelData.level1.length; i++) {
			String line = LevelData.level1[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					Node platform = createEntity(j * 60, i * 60, 60, 60, Color.BROWN);
					platforms.add(platform);
					break;
				case '2':
					Image image = new Image("/Images/Robber.png");
					ImageView coin = createEnemy(j * 60, i * 60, 60, 60, image);
					coins.add(coin);
					break;
				}
			}
		}
		Image image = new Image("/Images/tinyplayer-removebg-preview.png");
		player = createPlayer(0, 600, image);

		player.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();
			if (offset > 640 && offset < levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
			}
		});

		appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
	}

	private void update() {
		if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
			jumpPlayer();
		}
		if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
			movePlayerX(-5);
		}
		if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5) {
			movePlayerX(5);
		}
		if (isPressed(KeyCode.P)) {
		initMainmenu();
		running = false;
		}

		if (playerVelocity.getY() < 10) {
			playerVelocity = playerVelocity.add(0, 1);
		}
		movePlayery((int) playerVelocity.getY());

		for (Node coin : coins) {
			if (player.getBoundsInParent().intersects(coin.getBoundsInParent())) {
				coin.getProperties().put("alive", false);
				dialogEvent = true;
				running = false;
			}
		}

		for (Iterator<ImageView> it = coins.iterator(); it.hasNext();) {
			Node coin = it.next();
			if (!(Boolean) coin.getProperties().get("alive")) {
				it.remove();
				gameRoot.getChildren().remove(coin);
			}
		}

	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private void jumpPlayer() {
		if (canJump) {
			playerVelocity = playerVelocity.add(0, -30);
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
						if (player.getTranslateY() + 40 == platform.getTranslateY()) {
							player.setTranslateY(player.getTranslateY() - 1);
							canJump = true;
							return;
						}
					} else {
						if (player.getTranslateY() == platform.getTranslateY() + 60) {
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
	
	private ImageView createEnemy(int x, int y, int w, int h, Image image) {
		ImageView enemy = new ImageView();
		enemy.setFitHeight(h);
		enemy.setFitWidth(w);
		enemy.setImage(image);
		enemy.setTranslateX(x);
		enemy.setTranslateY(y);
		enemy.getProperties().put("alive", true);
		gameRoot.getChildren().add(enemy);
		return enemy;
	}

	private ImageView createPlayer(int x, int y, Image image) {
		ImageView entity = new ImageView();
		entity.setFitHeight(40);
		entity.setFitWidth(40);
		entity.setImage(image);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		gameRoot.getChildren().add(entity);
		return entity;

	}

private void MainMenu() {
	viewhandler.openView("Chapter1");
}

}
