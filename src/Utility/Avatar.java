package Utility;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

public class Avatar extends Application {
	private Pane avatarRoot = new Pane();
	private Pane appRoot = new Pane();
	private ImageView hat;
	private ImageView rightshoe;
	private ImageView leftshoe;
	private ImageView righthand;
	private ImageView lefthand;
	
	
	Item item = new Item("head_helmet", new Image("/Images/duck-png-8.png"));
	List<Item>Items = new ArrayList<>();
	List<ImageView>inventory = new ArrayList<>();
	FlowPane flow = new FlowPane();
	private ImageView currentNode;

	public static void main(String[] args) {
		Application.launch(Avatar.class);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initcontent();
		Items.add(item);
		

		
		

		Scene scene = new Scene(appRoot);
		primaryStage.setScene(scene);

		primaryStage.show();

		
	}

	private ImageView createHitbox(int x, int y, int h, int w, String type, Image image) {
		ImageView hitbox = new ImageView();
		hitbox.setImage(image);
		hitbox.setTranslateX(x);
		hitbox.setTranslateY(y);
		hitbox.setFitHeight(h);
		hitbox.setFitWidth(w);
		String bodypart = type;
		hitbox.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			DressAvatar(bodypart);
		});
//		avatarRoot.getChildren().add(hitbox);
		return hitbox;
	}
	
	private ImageView createAvatar(int x, int y, int h, int w, Image image) {
		ImageView avatar = new ImageView(image);
		avatar.setTranslateX(x);
		avatar.setTranslateY(y);
		avatar.setFitHeight(h);
		avatar.setFitWidth(w);
		
		avatarRoot.getChildren().add(avatar);
		
		return avatar;
		
	}
	
	private void initcontent() {
			createAvatar(0, 0, 500, 500, new Image("/Images/Avatar.png"));
			hat = createHitbox(155, 75, 110, 110, "head", new Image("/Images/plus.png"));
			leftshoe = createHitbox(80,400, 55, 55, "feet", new Image("/Images/plus.png"));
			rightshoe = createHitbox(250,400, 55, 55, "feet", new Image("/Images/plus.png"));
			
			
			appRoot.getChildren().addAll(avatarRoot);
			avatarRoot.getChildren().addAll(hat,leftshoe,rightshoe);
		
	}

	private void DressAvatar(String bodypart) {
		Button choose = new Button("Select");
		VBox vbox = new VBox(choose);
		switch (bodypart) {
		case "head":
			loadItems(bodypart);
			break;
		case "right hand":
			loadItems(bodypart);
			break;
		case "left hand":
			loadItems(bodypart);
			break;
		case "feet":
			loadItems(bodypart);
			break;

		}
		choose.setOnAction(event -> {
			if(bodypart.equals("head")) {
				hat.setImage(currentNode.getImage());
				vbox.getChildren().clear();
				appRoot.getChildren().remove(vbox);
				flow.getChildren().clear();
				avatarRoot.getChildren().remove(flow);	
			} else if(bodypart.equals("right hand")) {
				righthand.setImage(currentNode.getImage());
				vbox.getChildren().clear();
				appRoot.getChildren().remove(vbox);
				flow.getChildren().clear();
				avatarRoot.getChildren().remove(flow);
			} else if(bodypart.equals("left hand")) {
				lefthand.setImage(currentNode.getImage());
				vbox.getChildren().clear();
				appRoot.getChildren().remove(vbox);
				flow.getChildren().clear();
				avatarRoot.getChildren().remove(flow);
			} else if (bodypart.equals("feet")) {
				leftshoe.setImage(currentNode.getImage());
				rightshoe.setImage(currentNode.getImage());
				vbox.getChildren().clear();
				appRoot.getChildren().remove(vbox);
				flow.getChildren().clear();
				avatarRoot.getChildren().remove(flow);
			}
			
		});
		vbox.setTranslateX(60);
		vbox.setTranslateY(60);
		appRoot.getChildren().add(vbox);
	}
	
	private void loadItems(String bodypart) {
		flow.setPrefWrapLength(170);
		
		
		for(int i = 0; i<Items.size(); i++) {
			if(Items.get(i).getName().contains(bodypart)) {
				createItemForInventory(Items.get(i).getImage());
			}
			
			
			
		}
		avatarRoot.getChildren().add(flow);
		
	}
	
	private Node createItemForInventory(ImageView image) {
		ImageView item = image;
		item.setFitHeight(85);
		item.setFitWidth(85);
		item.addEventFilter(MouseEvent.MOUSE_CLICKED, event->{
			currentNode = item;
		});
		flow.setTranslateX(80);
		flow.setTranslateY(80);
		flow.getChildren().add(item);
		
		return item;
		
	}
	
}


