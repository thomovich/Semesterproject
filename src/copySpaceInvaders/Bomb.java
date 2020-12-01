package copySpaceInvaders;

import javax.swing.ImageIcon;

public class Bomb extends Sprite {

    private boolean destroyed;

    public Bomb(int x, int y) {

        initBomb(x, y);
    }

    private void initBomb(int x, int y) {

        setDestroyed(true);

        this.x = x;
        this.y = y;

        String alienImg = "../Images/missileRotate.png";
        ImageIcon ii = loadImage(alienImg);

        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
