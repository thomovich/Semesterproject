package Gamestarter;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import View.ViewHandler;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
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
import javafx.util.Duration;

public class AdditionGame {
	private static int delayTimer;
	private static int IndexObsticle;
	private static int IndexObsticleOld;
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> trees = new ArrayList<Node>();
	private Node car;
	private VBox vbox;
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private boolean dialogEvent = false, running = true;
	private ViewHandler viewhandler;
	private Point2D playerVelocity = new Point2D(0, 0);
	private int levelWidth;
	Label text = new Label();

	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int halfwidth = (int) (screenSize.getWidth() / 2);
	private int width = (int) (screenSize.getWidth());
	private int height = (int) (screenSize.getHeight());
//	private Node label;
	private VBox box;

	public void start(Stage primaryStage, ViewHandler viewhandler) throws Exception {
		this.viewhandler = viewhandler;

		initcontent();

		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Addition Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(height);
		primaryStage.setWidth(width);
		primaryStage.centerOnScreen();
		primaryStage.show();

		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if (running) {
					Movement();
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

	protected void initMathProblem() {
		AdditionQuestions problems = new AdditionQuestions();
		Text question = new Text(problems.createproblem());
		TextField fieldAnswer = new TextField();
		Text Answer = new Text(problems.getAnswer());
		Answer.setVisible(false);
		Text correct = new Text();
		correct.setVisible(false);
		Button btnSubmit = new Button("Submit");
		btnSubmit.setOnAction(event -> {
			if (Answer.getText().equals(fieldAnswer.getText())) {
				Answer.setVisible(true);

				correct.setText("Your answer is correct. Congrats bro!");
				correct.setVisible(true);

				appRoot.getChildren().add(correct);

				try {

					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				dialogEvent = false;
				running = true;
				appRoot.getChildren().remove(box);

			}
		});
		box = new VBox(10, question, fieldAnswer, btnSubmit, Answer, correct);
		box.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY)));
		box.setTranslateX(650);
		box.setTranslateY(450);
		appRoot.getChildren().addAll(box);
	}

	private void Movement() {
		if (isPressed(KeyCode.RIGHT) && car.getTranslateX() + 40 <= levelWidth - 5) {
			moveright(7);
			car.setScaleX(1);
		}
		if (isPressed(KeyCode.LEFT) && car.getTranslateX() + 40 <= levelWidth - 5) {
			moveleft(7);
			car.setScaleX(-1);
		}

		if (isPressed(KeyCode.P)) {
			initMainmenu();
			running = false;
		}

		for (Node trees : trees) {
			if (car.getBoundsInParent().intersects(trees.getBoundsInParent())) {
				trees.getProperties().put("alive", false);
				dialogEvent = true;
				running = false;
			}
		}

		for (Iterator<Node> it = trees.iterator(); it.hasNext();) {
			Node trees = it.next();
			if (!(Boolean) trees.getProperties().get("alive")) {
				it.remove();
				gameRoot.getChildren().remove(trees);
			}
		}

	}

	private void moveright(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node trees : trees) {
				if (car.getBoundsInParent().intersects(trees.getBoundsInParent())) {
					if (movingRight) {
						if (car.getTranslateX() + 40 == trees.getTranslateX()) {
							return;
						}
					} else {
						if (car.getTranslateX() == trees.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			car.setTranslateX(car.getTranslateX() + (movingRight ? 1 : -1));
		}

	}

	private void moveleft(int value) {
		boolean movingLeft = value < 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node trees : trees) {
				if (car.getBoundsInParent().intersects(trees.getBoundsInParent())) {
					if (movingLeft) {
						if (car.getTranslateX() + 40 == trees.getTranslateX()) {
							return;
						}
					} else {
						if (car.getTranslateX() == trees.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			car.setTranslateX(car.getTranslateX() + (movingLeft ? 1 : -1));
		}

	}

	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private void MainMenu() {
		viewhandler.openView("Chapter4");
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

//	private void timer() {
//		PauseTransition text = new PauseTransition(Duration.seconds(8));
//		text.setOnFinished(event -> label.setVisible(false));
//		text.play();
//	}

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
	

	private void initcontent() {
		Rectangle background = new Rectangle(width, height);
		background.setFill(new ImagePattern(new Image("/Images/landscape.png")));
		levelWidth = map.level1[0].length() * width / 32;
					Image tree = new Image("/Images/tree.png");
					ImageView obsticle = createImageEntity(  60, 60, 40, 70, tree);
					trees.add(obsticle);
					//Her skal der laves en generator til at spawne træer


		Image image = new Image("/Images/car1.png");
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
