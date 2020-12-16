package Utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
	private String name;
	private Image images;

	

	private ImageView image;

	public Item(String name, Image image) {
		this.images = image;
		this.name = name;
		this.image = new ImageView(image);
	}
	
	public Image getImages() {
		return images;
	}

	public String getName() {
		return name;
	}

	public ImageView getImage() {
		return image;
	}

}
