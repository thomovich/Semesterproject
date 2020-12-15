package Space;

import javax.swing.ImageIcon;

public class Alien extends Sprite {

    private Bomb bomb;
    
    private int rowPosition;
    
    private int columnPosition;
    
    private boolean isHit;
    
    private String question;
    
	public String getQuestion() {
		return question;
	}
	
	public Alien(int x, int y) {
		this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        String alienImg = "../Images/alienOrange.png";
        ImageIcon ii = loadImage(alienImg);

        setImage(ii.getImage());
    	
    }
    public int getRowPosition() {
		return rowPosition;
	}
	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}
	public int getColumnPosition() {
		return columnPosition;
	}
	public void setColumnPosition(int columnPosition) {
		this.columnPosition = columnPosition;
		
	}
	public boolean isHit() {
		return isHit;
	}
	
	public void setHit(boolean hit) {
		this.isHit=hit;
	}

    public void act(int direction) {

        this.x += direction;
    }

    public Bomb getBomb() {

        return bomb;
    }
    
    public void unSetBomb() {
    	bomb=null;
    }
    
    
    
    public String setNumber(String number) {
    	
    	switch(number) {
    	
    	case "36":
    		return question="what is 4*9?";
    	case "108":
    		return question="what is 9*12?";
    	case "70":
    		return question="what is 14*5?";
    	case "130":
    		return question="what is 13*10?";
    	case "72":
    		return question="what is 8*9?";
       	case "135":
    		return question="what is 15*9?";
    	case "35":
    		return question="what is 5*7?";
    	case "63":
    		return question="what is 7*9?";
    	case "64":
    		return question="what is 8*8?";
    	case "126":
    		return question="what is 14*9?";
    	case "88":
    		return question="what is 8*11?";
    	case "120":
    		return question="what is 10*12?";
    	case "21":
    		return question="what is 3*7?";
    	case "48":
    		return question="what is 6*8?";
    	case "300":
    		return question="what is 20*15?";
    	case "55":
    		return question="what is 5*11?";
    	case "144":
    		return question="what is 12*12?";
    	case "56":
    		return question="what is 8*7?";
    	case "60":
    		return question="what is 5*12?";
    	case "20":
    		return question="what is 4*5?";
    	case "45":
    		return question="what is 9*5?";
    	case "19":
    		return question="what is 1*19?";
    	case "77":
    		return question="what is 11*7?";
    	}
    	return null;
    }
}
