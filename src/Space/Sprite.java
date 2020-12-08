package Space;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sprite {

	private boolean visible;
	private Image image;
	private boolean dying;

	int x;
	int y;
	int dx;

	public Sprite() {

		visible = true;
	}

	public void die() {

		visible = false;
	}

	public boolean isVisible() {

		return visible;
	}

	protected void setVisible(boolean visible) {

		this.visible = visible;
	}

	public void setImage(Image image) {

		this.image = image;
	}

	public Image getImage() {

		return image;
	}

	public void setX(int x) {

		this.x = x;
	}

	public void setY(int y) {

		this.y = y;
	}

	public int getY() {

		return y;
	}

	public int getX() {

		return x;
	}

	public void setDying(boolean dying) {

		this.dying = dying;
	}

	public boolean isDying() {

		return this.dying;
	}

	protected ImageIcon loadImage(String path) {

		ImageIcon ii = createImageIcon(path);

		return ii;


	}

	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
