package Gamestarter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import View.ViewHandler;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	private ArrayList<Node> booths = new ArrayList<Node>();
	private Text lasttext;
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private Node currentcar;
	private VBox vbox;
	private boolean running = true;
	
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
			if(running) {
				update();
			}
			
		}
	};
	timer.start();
	
	}
	
	private void initcontent() {
		
	for (int i = 0; i<9; i++) {
	Image image = new Image("/Images/parkingcar.png");
	createImageEntity(130*i,200,60,40,image, i + 1);
	}
	for (int i =0; i<10; i++) {
	createEntity(110*(i+1),50, 20, 80,Color.BLUE);
	createEntity(110*(i+1)+60,50, 20, 80,Color.BLUE);
	createEntity(110*(i+1), 50, 80, 20, Color.BLACK);
	
	}
	
	
	
	appRoot.getChildren().add(gameRoot);
	}
	
	
	
	
	private ImageView createImageEntity(int x, int y, int w, int h, Image image, int i) {
		ImageView car = new ImageView();
		car.setFitHeight(h);
		car.setFitWidth(w);
		car.setImage(image);
		Text text = new Text("" + i);
		
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		text.setFill(Color.YELLOW);
		StackPane pane = new StackPane();
		pane.getChildren().add(car);
		text.setTranslateX(-5);
		pane.getChildren().add(text);
		pane.setAlignment(Pos.CENTER);
		pane.setTranslateX(x);
		pane.setTranslateY(y);
		cars.add(pane);
		gameRoot.getChildren().add(pane);
		pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
		currentcar = pane;
		text.setFill(Color.GREEN);
		if(lasttext!=null) {
			lasttext.setFill(Color.YELLOW);
		}
		lasttext = text;
		});
		return car;
		
	}
	
	private Node createEntity(int x, int y, int w, int h, Color color) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(color);
		gameRoot.getChildren().add(entity);
		return entity;
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
		
		if(isPressed(KeyCode.P)) {
			running = false;
			initMainmenu();
		}
		
		for(Node booth: booths) {
			if(currentcar.getBoundsInParent().intersects(booth.getBoundsInParent())){
			booth.getProperties().put("alive", false);
			}
		}
		
		for(Iterator<Node> it = booths.iterator(); it.hasNext();) {
			Node booth = it.next();
			if(!(Boolean) booth.getProperties().get("alive")) {
				it.remove();
			}
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
	
	private void initMainmenu() {

		Font font = Font.font(32);

		Button btnResume = new Button("Resume Game");
		btnResume.setOnAction(event -> {
			running = true;
			appRoot.getChildren().remove(vbox);

		});
		btnResume.setFont(font);

		Button mainMenu = new Button("Main Menu");
		mainMenu.setOnAction(event -> viewhandler.openView("Chapter1"));
		mainMenu.setFont(font);
		vbox = new VBox(20, btnResume, mainMenu);
		vbox.setTranslateX(400);
		vbox.setTranslateY(400);
		appRoot.getChildren().add(vbox);

	}

}
