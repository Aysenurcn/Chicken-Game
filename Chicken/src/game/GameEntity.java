package game;

import game.Game;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class GameEntity{

	protected Sprite sprite;

	public GameEntity(Sprite sprite) {
		this.sprite = sprite;
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public void draw(GraphicsContext ctx) {
		sprite.draw(ctx);
	}

	public double getX() {
		return sprite.getX();
	}

	public double getY() {
		return sprite.getY();
	}

	public void setXY(double x, double y) {
		sprite.setXY(x, y);
	}
	
	public void setX(double x) {
		sprite.setX(x);
	}
	
	public void setY(double y) {
		sprite.setY(y);
	}

	public double getHeight() {
		return sprite.getHeight();
	}

	public double getWidth() {
		return sprite.getWidth();
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
        
        public String getName() {
            return "Default";
	}
        
        public void update(Game game){
            System.out.println("Forgot update function");
        }
        
        public boolean collidesWith (GameEntity other) {
            if (other.getName() == "Default") {
			return (checkCollision(other));
            }else {return false;}
	}

	public boolean checkCollision(GameEntity other) {
		final Rectangle otherBox = other.getCollisionBox();
		final Rectangle myBox = getCollisionBox();
		return otherBox.intersects(myBox.getBoundsInParent());
	}
}

