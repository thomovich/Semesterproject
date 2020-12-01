package copySpaceInvaders;

import javax.swing.ImageIcon;

public class Alien extends Sprite {

    private Bomb bomb;

    public Alien(int x, int y,boolean correct) {
    	
    	
    }
    public Alien(int x, int y) {

        initAlien(x, y);
    }

    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        String alienImg = "../Images/alien1.jpg";
        ImageIcon ii = loadImage(alienImg);

        setImage(ii.getImage());
    }
    
    public void initAnswer(int x,int y, boolean correct) {
    	
    	this.x = x;
        this.y = y;

        if(correct) {
        	String correctAnswer = "../Images/";
        	//Fisse
        	//Mere fisse
        }
    }
    
    

    public void act(int direction) {

        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }
}
