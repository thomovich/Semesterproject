package Gamestarter;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.stage.Stage;



public class DuckGame {
	private final int WIDTH = 1280;
	private final int HEIGHT = 720;
	private boolean running = true;
	
public void start (Stage primaryStage) {
	Group root = new Group();
	primaryStage.setTitle("Duck game");
	primaryStage.setHeight(HEIGHT);
	primaryStage.setWidth(WIDTH);
	primaryStage.centerOnScreen();
	primaryStage.show();
	
	AnimationTimer timer = new AnimationTimer() {
		public void handle(long now) {
			if(running) {
				
			}
		}
		
	};
}
}
