package Gamestarter;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.andrewmatzureff.input.Mouse;

import Utility.Score;
import View.ViewHandler;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DuckGame {
	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Scene scene;
	private VBox Vbox;
	private int errors;
	private boolean dialogevent = true;
	private ArrayList<Node> ducks = new ArrayList<Node>();
	private boolean running = true;
	private ViewHandler viewhandler;
	String[] musicfiles = {"animals038.mp3"};
	
	public void start(Stage primaryStage, ViewHandler viewhandler) {
		initcontent();
		this.viewhandler = viewhandler;
		
		
		
		
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
					if((int)(Math.random()*20) == 2) {
						createContent(new Image("/Images/duck-png-8.png"), 32 * j, 32 * i, 32, 32);
					}
					
				break;
				
				case '2':
					ImageView pond = new ImageView(new Image("/Images/pond.png"));
					pond.setLayoutX(32*j);
					pond.setLayoutY(32*i);
					gameRoot.getChildren().add(pond);
				break;
				}
			}
		}
		appRoot.getChildren().addAll(gameRoot);
	}
	
	private void update() {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				for(Node duck: ducks) {
					if(duck.contains(t.getX(),t.getY()) && dialogevent) {
						dialogevent = false;
						createQuestion();
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
				gameRoot.getChildren().remove(duck);
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
		gameRoot.getChildren().add(entity);
		return entity;
	}
	
	private void createQuestion() {
		int number1 = (int) (Math.random()*10) + 1;
		int number2 = (int) (Math.random()*10) + 1;
		Text question = new Text("What is the remainder of this:" + number1 + "/" + number2);
		TextField fieldAnswer = new TextField();
		String answer = (number1%number2) + "";
		System.out.println(answer);
		Button btnSubmit = new Button("Sumbit");
		btnSubmit.setOnAction(event -> {
			if(fieldAnswer.getText().equals(answer)) {
				dialogevent = true;
				gameRoot.getChildren().remove(Vbox);
			} else {
				errors++;
			}
			if(ducks.size()==0) {
				endgame();
			}
		});
		
		
		
		Vbox = new VBox(10, question, fieldAnswer, btnSubmit);
		Vbox.setBackground(new Background(new BackgroundFill(Color.YELLOW,CornerRadii.EMPTY, Insets.EMPTY)));
		Vbox.setTranslateX(400);
		Vbox.setTranslateY(400);
		gameRoot.getChildren().addAll(Vbox);
	}
	
	private void endgame() {
		running = false;
		Score score = new Score(errors, "duckgame");
		score.setOnCloseRequest(event->{
			viewhandler.openView("Chapter1");
		});
	}
	
}
