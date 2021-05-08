package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite{

	private final Image image;
	private double x, y;
	
	public Sprite(Image image) {
		this.image = image;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void setXY(double x, double y) {
		setX(x);
		setY(y);
	}

	public double getHeight() {
		return image.getHeight();
	}
	
	public double getWidth() {
		return image.getWidth();
	}
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}
	
}
