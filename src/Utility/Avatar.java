package Utility;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;

public class Avatar extends Application {
	private Pane avatarRoot = new Pane();
	private Pane appRoot = new Pane();

	public static void main(String[] args) {
		Application.launch(Avatar.class);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initcontent();
		

		
		

		Scene scene = new Scene(appRoot);
		primaryStage.setScene(scene);

		primaryStage.show();

		
	}

	private Node createHitbox(int x, int y, int h, int w, String type) {
		Rectangle hitbox = new Rectangle(h, w);
		hitbox.setFill(Color.BLACK);
		hitbox.setTranslateX(x);
		hitbox.setTranslateY(y);
		String bodypart = type;
		hitbox.setFill(Color.BLACK);
		hitbox.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			DressAvatar(bodypart);
		});
		hitbox.toFront();
		avatarRoot.getChildren().add(hitbox);
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
			createHitbox(155, 75, 110, 110, "head");
			createHitbox(80,400, 55, 55, "feet");
			createHitbox(250,400, 55, 55, "feet");
			
			
			appRoot.getChildren().addAll(avatarRoot);
		
	}

	private void DressAvatar(String bodypart) {
		Button choose = new Button("Select");
		VBox vbox = new VBox(choose);
		switch (bodypart) {
		case "head":
			System.out.println(bodypart);
			break;
		case "right hand":
			break;
		case "left hand":
			break;
		case "feet":
			break;

		}
		choose.setOnAction(event -> {
			
			appRoot.getChildren().remove(vbox);

		});
		appRoot.getChildren().add(vbox);
	}

}
