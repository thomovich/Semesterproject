package Gamestarter;

import com.andrewmatzureff.input.Mouse;

import View.ViewHandler;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DuckGame {
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	
	public void start(Stage primaryStage, ViewHandler viewhandler) {
		initcontent();
		ImageView targetdata = new ImageView(new Image("/Images/duck-png-8.png"));
		Rectangle rec = new Rectangle(100,100);
		rec.setX(100);
		rec.setY(100);
		rec.setFill(Color.TRANSPARENT);
		targetdata.setTranslateX(100);
		targetdata.setTranslateY(100);
		targetdata.setFitWidth(100);
		targetdata.setFitHeight(100);
		
		appRoot.getChildren().add(rec);
		appRoot.getChildren().add(targetdata);
		
		Scene scene = new Scene(appRoot);
		System.out.println(rec.getX());
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				System.out.println(t.getX());
				if(rec.contains(t.getX(), t.getY())) {
					System.out.println("duck clicked");
				}
			}
		});
		primaryStage.setTitle("Duck Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(720);
		primaryStage.setWidth(1280);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				update();
			}

			
		};
		timer.start();
		
	}

	private void initcontent() {
		
	}
	
	private void update() {
		
	}
}
