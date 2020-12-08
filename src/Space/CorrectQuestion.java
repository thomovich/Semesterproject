package Space;

import javax.swing.ImageIcon;

public class CorrectQuestion extends Sprite {

    public CorrectQuestion(int x, int y) {

        initQuestion(x, y);
    }

    private void initQuestion(int x, int y) {

        this.x = x;
        this.y = y;

        Bomb bomb = new Bomb(x, y);

        String alienImg = "../Images/alien1.jpg";
        ImageIcon ii = loadImage(alienImg);

        setImage(ii.getImage());
    }
    
    

    public void act(int direction) {

        this.x += direction;
    }
    
}
