package Gamestarter;


import java.util.ArrayList;
import java.util.HashMap;

import View.ViewHandler;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OldLadyGame {
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private int width = 1280;
	private int height = 720;
	private ArrayList<Node> cars = new ArrayList<Node>();
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private HashMap<MouseButton, Boolean> clicks = new HashMap<MouseButton, Boolean>();
	private Node currentcar;
	
	private ViewHandler viewhandler;
	
	public void Start(Stage PrimaryStage, ViewHandler viewhandler) {
	this.viewhandler = viewhandler;
	
	initcontent();
	currentcar = cars.get(0);
	PrimaryStage.setTitle("Old lady game");
	Scene scene = new Scene(appRoot);
	scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
	scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
	PrimaryStage.setScene(scene);
	PrimaryStage.setHeight(height);
	PrimaryStage.setWidth(width);
	PrimaryStage.centerOnScreen();
	PrimaryStage.show();
	
	AnimationTimer timer = new AnimationTimer() {
		public void handle(long now) {
			update();
		}
	};
	timer.start();
	
	}
	
	private void initcontent() {
		
	
	Image image = new Image("/Images/parkingcar.png");
	createImageEntity(50,50,40,40,image);
	createImageEntity(100,100,40,40,image);
	appRoot.getChildren().add(gameRoot);
	}
	
	
	
	
	private ImageView createImageEntity(int x, int y, int w, int h, Image image) {
		ImageView car = new ImageView();
		car.setFitHeight(h);
		car.setFitWidth(w);
		car.setImage(image);
		Text text = new Text("8");
		
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		StackPane pane = new StackPane();
		pane.getChildren().add(car);
		pane.getChildren().add(text);
		pane.setAlignment(Pos.CENTER);
		pane.setTranslateX(x);
		pane.setTranslateY(y);
		cars.add(pane);
		gameRoot.getChildren().add(pane);
		pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> currentcar = pane);
		return car;
		
	}
	
	private void update() {
		if(isPressed(KeyCode.A) && currentcar.getTranslateX()>= 5) {
			moveCarX(-5);
			currentcar.setRotate(180);
		}
		
		if(isPressed(KeyCode.D) && currentcar.getTranslateX() + 40 <= 1240) {
			moveCarX(5);
			currentcar.setRotate(0);
		}
		
		if(isPressed(KeyCode.S) && currentcar.getTranslateY() + 50<=680) {
			moveCarY(5);
			currentcar.setRotate(90);
		}
		
		if(isPressed(KeyCode.W) && currentcar.getTranslateY() >=10) {
			moveCarY(-5);
			currentcar.setRotate(270);
		}
		
		
		
		
	}
	
	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	
	
	private void moveCarX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			currentcar.setTranslateX(currentcar.getTranslateX() + (movingRight ? 1 : -1));
		}

	}
	
	private void moveCarY(int value) {
		boolean movingdown = value > 0;
		for(int i = 0; i< Math.abs(value); i++) {
			currentcar.setTranslateY(currentcar.getTranslateY()+ (movingdown ? 1: -1));
		}
	}

}
