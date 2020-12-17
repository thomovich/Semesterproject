package View;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;

import Utility.MenuItem;
import Utility.Title;

public class FrontPage {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private ViewHandler viewhandler;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Chapter 1", () -> {openview("Chapter1");}),
            new Pair<String, Runnable>("Chapter 2", () -> {openview("Chapter2");}),
            new Pair<String, Runnable>("Chapter 3", () -> {openview("Chapter3");}),
            new Pair<String, Runnable>("Chapter 4", () -> {openview("Chapter4");}),
            new Pair<String, Runnable>("Chapter 5", () -> {openview("Chapter5");}),
            new Pair<String, Runnable>("Teacher", () -> {openview("Chapter6");}),
            new Pair<String, Runnable>("Avatar", () -> {openview("Avatar");}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );
    
    private List<ImageView> images = Arrays.asList(
    		CreateImage(40,42, new Image("/Images/Division.png")),
    		CreateImage(40,42, new Image("/Images/plus.png")),
    		CreateImage(40,42, new Image("/Images/Minus.png")),
    		CreateImage(40,42, new Image("/Images/plus.png")),
    		CreateImage(40,42, new Image("/Images/Gange.png")),
    		CreateImage(40,42, new Image("/Images/Teacher.png")),
    		CreateImage(40,42, new Image("/Images/plus.png")),
    		CreateImage(40,42, new Image("/Images/plus.png"))
    		
    		);

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private VBox imageBox = new VBox(-5);
    private Line line;

    private Parent createContent() {
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);
        addPictures(lineX + 10, lineY + 5);

        startAnimation();

        return root;
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image(this.getClass().getResource("../Images/Worldofmath.jpg").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        root.getChildren().add(imageView);
        
    }

    private void addTitle() {
        Title title = new Title("World OF Math");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x+50, y);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setByX(3);
        st.setToY(1);
        st.setOnFinished(e -> {
            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
                for(int j = 0; j<1; j++) {
                	Node m = imageBox.getChildren().get(i);
                	TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1 + i * 0.15), m);
                	transition2.setToY(0);
                	transition2.play();
       
                }
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }
    
    private void addPictures(double x, double y) {
    	imageBox.setTranslateX(x-50);
    	imageBox.setTranslateY(y);
    	images.forEach(data -> {
    		Rectangle clip = new Rectangle(1000,1000);
    		clip.translateYProperty().bind(data.translateYProperty().negate());
    		data.setClip(clip);
    		data.setTranslateY(-300);
    		imageBox.getChildren().addAll(data);
    		
    	});
    	root.getChildren().addAll(imageBox);
    }
    
    private ImageView CreateImage(int w, int h, Image image) {
    	ImageView maths = new ImageView();
    	maths.setFitHeight(h);
    	maths.setFitWidth(w);
    	maths.setImage(image);
		return maths;
    	
    }


    public void start(Stage primaryStage, ViewHandler viewhandler){
        this.viewhandler = viewhandler;
    	Scene scene = new Scene(createContent());
        primaryStage.setTitle("World Of Math");
        primaryStage.setScene(scene);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

  
    
    public void openview(String id) {
    	
    	switch(id) {
    	case "Chapter1":
    		viewhandler.openView("Chapter1");
    		break;
    	case "Chapter2":
    		viewhandler.openView("Chapter2");
    		break;
    	case "Chapter3":
    		viewhandler.openView("Chapter3");
    		break;
    	case "Chapter4":
    		viewhandler.openView("Chapter4");
    		break;
    	case "Chapter5":
    		viewhandler.openView("Chapter5");
    		break;
    	case "Chapter6":
    		viewhandler.openView("Chapter6");
    		break;
    	case "Avatar":
    		try {
				viewhandler.openAvatar();
			} catch (Exception e) {
				e.printStackTrace();
			}
    		break;
    	}
    	
    }
}