package Gamestarter;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.andrewmatzureff.input.Mouse;

import View.ViewHandler;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DuckGame {
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Scene scene;
	private ArrayList<Node> ducks = new ArrayList<Node>();
	private boolean running = true;
	String[] musicfiles = {"animals038.mp3"};
	
	public void start(Stage primaryStage, ViewHandler viewhandler) {
		initcontent();
		
		
		
		
		scene = new Scene(appRoot);
		
		primaryStage.setTitle("Duck Game");
		primaryStage.setScene(scene);
		primaryStage.setHeight(720);
		primaryStage.setWidth(1280);
		primaryStage.centerOnScreen();
		primaryStage.show();
		
		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {
				if(running) {
					update();
				}
				
			}

			
		};
		timer.start();
		
	}

	private void initcontent() {
		for(int i = 0; i<LevelData.DUCKGAMELEVEL.length; i++) {
			String line = LevelData.DUCKGAMELEVEL[i];
			for(int j = 0; j< line.length(); j++) {
				switch(line.charAt(j)) {
				case '0':
				break;
				
				case '1':
					if((int)(Math.random()*2) == 0) {
						createContent(new Image("/Images/duck-png-8.png"), 32 * j, 32 * i, 32, 32);
					}
					
				break;
				
				case '2':
					ImageView pond = new ImageView(new Image("/Images/pond.png"));
					pond.setLayoutX(32*j);
					pond.setLayoutY(32*i);
					appRoot.getChildren().add(pond);
				break;
				}
			}
		}
		
	}
	
	private void update() {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				for(Node duck: ducks) {
					if(duck.contains(t.getX(),t.getY())) {
						
						Media media = new Media(new File(musicfiles[0]).toURI().toString()); 
						MediaPlayer mediaPlayer = new MediaPlayer(media);
						mediaPlayer.play();
						System.out.println("Duck clicked");
						duck.getProperties().put("alive", false);
					}
				}
			}
		});
		
		for(Iterator<Node> it = ducks.iterator(); it.hasNext();) {
			
			Node duck = it.next();
			if(!(Boolean)duck.getProperties().get("alive")) {
				it.remove();
				appRoot.getChildren().remove(duck);
			}
			
		}
		
	}
	
	private Node createContent(Image image, int x, int y, int w, int h) {
		ImageView entity = new ImageView();
		entity.getProperties().put("alive", true);
		entity.setFitHeight(h);
		entity.setFitWidth(w);
		entity.setX(x);
		entity.setY(y);
		entity.setImage(image);
		ducks.add(entity);
		appRoot.getChildren().add(entity);
		return entity;
	}
	
	private void createQuestion() {
		
	}
}
