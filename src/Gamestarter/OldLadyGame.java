package Gamestarter;

import java.util.ArrayList;
import java.util.HashMap;

import View.ViewHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OldLadyGame {
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private int width = 1280;
	private int height = 720;
	ArrayList<Node> cars = new ArrayList<Node>();
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private Node currentcar;
	
	private ViewHandler viewhandler;
	
	public void Start(Stage PrimaryStage, ViewHandler viewhandler) {
	this.viewhandler = viewhandler;
	PrimaryStage.setTitle("Old lady game");
	Scene scene = new Scene(appRoot);
	PrimaryStage.setScene(scene);
	PrimaryStage.setHeight(height);
	PrimaryStage.setWidth(width);
	PrimaryStage.centerOnScreen();
	PrimaryStage.show();
	
	
	}
	
	private void initcontent() {
		
		
	Image image = new Image("/Images/parkingcar.png");
	createImageEntity(20,20,40,40,image);
	appRoot.getChildren().add(gameRoot);
	}
	
	private Node createContent() {
		
		return appRoot;
		
	}
	
	private ImageView createImageEntity(int x, int y, int w, int h, Image image) {
		ImageView car = new ImageView();
		car.setFitHeight(h);
		car.setFitWidth(w);
		car.setImage(image);
		car.setTranslateX(x);
		car.setTranslateY(y);
		gameRoot.getChildren().add(car);
		cars.add(car);
		return car;
		
	}
	
	private void update() {
		if(isPressed(KeyCode.W) && currentcar.getTranslateY()>= 5) {
			moveCarX(-5);
		}
	}
	
	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}
	
	private void moveCarX(int value) {
		boolean movingRight = value > 0;
		for (int i = 0; i < Math.abs(value); i++) {
			for (Node car : cars) {
				if (currentcar.getBoundsInParent().intersects(car.getBoundsInParent())) {
					if (movingRight) {
						if (currentcar.getTranslateX() + 40 == car.getTranslateX()) {
							return;
						}
					} else {
						if (currentcar.getTranslateX() == car.getTranslateX() + 60) {
							return;
						}
					}
				}
			}
			currentcar.setTranslateX(currentcar.getTranslateX() + (movingRight ? 1 : -1));
		}

	}

}
