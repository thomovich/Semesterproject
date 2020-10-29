package Gamestarter;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class MayorGame{

	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Keyboard input");
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        primaryStage.setScene( theScene );
        primaryStage.setWidth(800);
        primaryStage.setHeight(656);
        primaryStage.setX(350);
        primaryStage.setX(300);
        
        Canvas canvas = new Canvas( 800, 656 );
        root.getChildren().add( canvas );
        
       
        ArrayList<String> input = new ArrayList<String>();
        
        theScene.setOnKeyPressed(
        		new EventHandler<KeyEvent>() 
        		{
        			public void handle(KeyEvent e) 
        			{
        				String code = e.getCode().toString();
        			if(!input.contains(code))
        				input.add(code);
        		}
 });
        
        theScene.setOnKeyReleased(
        		new EventHandler<KeyEvent>()
        		{
        			public void handle(KeyEvent e) {
        				String code = e.getCode().toString();
        				input.remove(code);
        			}
        		}
        		
        		
        		);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Sprite player = new Sprite();
        player.setImage("/Images/tinyplayer.png");
        player.setPosition(200, 0);
        
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        new AnimationTimer()
        {

			@Override
			public void handle(long currentNanoTime) { 
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
		        lastNanoTime.value = currentNanoTime;
			player.setVelocity(0, 0);
			if(input.contains("LEFT")) 
			player.addVelocity(-50, 0);
			
			if(input.contains("RIGHT"))
			player.addVelocity(50, 0);
			
			if(input.contains("UP"))
				player.addVelocity(0, -50);
			
			if(input.contains("DOWN"))
				player.addVelocity(0, 50);
			
			player.update(elapsedTime);
			
			
			
			gc.clearRect(0, 0, 512,512);
            player.render( gc );
			
			
		
			}
        	
        }.start();
        
    
         
        
        
        primaryStage.show();
             
        
	}
	
}
