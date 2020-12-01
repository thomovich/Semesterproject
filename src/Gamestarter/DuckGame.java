package Gamestarter;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class DuckGame {
	private final int WIDTH = 1280;
	private final int HEIGHT = 720;
	private boolean running = true;
	
public void start (Stage primaryStage) {
	Group root = new Group();
	Scene theScene = new Scene(root);
	primaryStage.setScene(theScene);
	Canvas canvas = new Canvas( 720, 1280 );
	root.getChildren().add(canvas);
	
	
	Circle targetData = new Circle(100,100,32);
	
	theScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			if(targetData.contains(e.getX(), e.getY() ) ) {
				//Få en popup med spørgsmål
			}
		}
	});
	
	GraphicsContext gc = canvas.getGraphicsContext2D();
	
	primaryStage.setTitle("Duck game");
	primaryStage.setHeight(HEIGHT);
	primaryStage.setWidth(WIDTH);
	primaryStage.centerOnScreen();
	primaryStage.show();
	Image duck = new Image("/Images/duck-png-8.png");
	
	AnimationTimer timer = new AnimationTimer() {
		public void handle(long now) {
			if(running) {
				gc.drawImage(duck,
						targetData.getTranslateX() - targetData.getRadius(),
						targetData.getTranslateY() - targetData.getRadius()	);
				
				
			}
		}
	};timer.start();
}
}
