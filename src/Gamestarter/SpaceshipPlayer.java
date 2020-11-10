package Gamestarter;

import java.awt.Graphics2D;
import java.net.URL;

import com.andrewmatzureff.input.Movement;
import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardID;
import com.joshuacrotts.standards.StdOps;
import com.sun.glass.events.KeyEvent;

public class SpaceshipPlayer extends StandardGameObject {

	private Movement left, right;
	
	private Spaceship spaceship;
	
	public SpaceshipPlayer(double x,double y,Spaceship spaceship) {
		super(x,y,StandardID.Player);
		
		this.spaceship=spaceship;
		
		this.currentSprite = StdOps.loadImage("Images/spaceship.jpg");
		this.left = new Movement(this,null,-2,0);
		this.right = new Movement(this,null,0,2);
		
		this.left.bind(spaceship.keyboard, KeyEvent.VK_A);
		this.right.bind(spaceship.keyboard, KeyEvent.VK_D);
		
	}
	
	@Override
	public void render(Graphics2D g2) {
		g2.drawImage(currentSprite, (int)x, (int)y, null);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
