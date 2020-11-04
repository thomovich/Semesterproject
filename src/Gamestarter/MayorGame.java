package Gamestarter;



import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;


public class MayorGame{
	public boolean falling = false;
	public boolean jumping = false;
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Mayor Game");
        
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
        Sprite platform = new Sprite();
        Sprite ground = new Sprite();
        player.setImage("/Images/tinyplayer-removebg-preview.png");
        platform.setImage("/images/Platform.png");
        ground.setImage("/images/Ground.png");
        player.setPosition(200, 0);
        platform.setPosition(100, 200);
        ground.setPosition(0, 400);
        
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
			
			if(input.contains("UP")&falling == false&jumping == false) {
				player.addVelocity(0, -1000);
				jumping = true;
			} else {
				jumping = false;
			}
				
			
			if(!(player.intersects(platform))&(!(player.intersects(ground)))&jumping==false) {
				player.addVelocity(0, 50);
				falling = true;
			} else {
			falling = false;
			player.addVelocity(0, 0);
			}
				

			player.update(elapsedTime);
			
			
			
			gc.clearRect(0, 0, 512,512);
            player.render( gc );
            platform.render(gc);
            ground.render(gc);
			
			
		
			}
        	
        }.start();
        
    
         
        
        
        primaryStage.show();
             
        
	}
	
}
