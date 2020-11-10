package Gamestarter;

import com.joshuacrotts.standards.StandardDraw;
import com.joshuacrotts.standards.StandardGame;
import com.joshuacrotts.standards.StandardHandler;

public class Spaceship extends StandardGame {

 
	
	private static final long serialVersionUID = 1L;
	private StandardHandler sh;
	
	private SpaceshipPlayer player;
	
	public Spaceship(int w,int h) {
		super(w,h,"spaceship");
		this.sh = new SpaceshipHandler();
		this.consoleFPS = false;
		
		this.player = new SpaceshipPlayer(300,720,this);
		
		this.StartGame();
	}

	@Override
	public void render() {
		StandardDraw.Handler(sh);
		
	}

	@Override
	public void tick() {
		StandardHandler.Handler(sh);
	}
	
	public static void main(String[] args) {
		 new Spaceship(800,800);
	}
}
