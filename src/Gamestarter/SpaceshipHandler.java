package Gamestarter;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.joshuacrotts.standards.StandardGameObject;
import com.joshuacrotts.standards.StandardHandler;

public class SpaceshipHandler extends StandardHandler {

	
	public SpaceshipHandler() {
		this.entities = new ArrayList<StandardGameObject>();
	}
	@Override
	public void tick() {
		
	}
	@Override
	public void render(Graphics2D g) {
	
		for(int i=0; i<this.entities.size();i++) {
			this.entities.get(i).render(g);
		}
	}
}
