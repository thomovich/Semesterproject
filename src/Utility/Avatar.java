package Utility;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;

public class Avatar extends Application{
	private Pane avatarRoot = new Pane();


public static void main(String[] args){
Application.launch(Avatar.class);
	
}

@Override
public void start(Stage primaryStage) throws Exception {
ImageView avatar = new ImageView(new Image("/Images/Avatar.png"));
	
	avatarRoot.getChildren().addAll(avatar);
	Scene scene = new Scene(avatarRoot);
	primaryStage.setScene(scene);
	primaryStage.show();
	
}

private Node createHitbox(int x, int y, int h, int w, String type) {
	Rectangle hitbox = new Rectangle(h,w);
	hitbox.setTranslateX(x);
	hitbox.setTranslateY(y);
	String bodypart = type;
	hitbox.setFill(Color.BLACK);
	hitbox.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
		DressAvatar(bodypart);
	});
	return hitbox;
}

private void DressAvatar(String bodypart) {
	Button choose = new Button("Select");
	switch(bodypart) {
	case "head":
	break;
	case "right hand":
	break;
	case "left hand":
	break;
	case "feet":
	break;
	
	}
	choose.setOnAction(event -> {
		
	});
}









}
