package game;

import java.util.Set;

import game.GameEntity;
import game.Sprite;
import game.GameInput;
import game.Game;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Chicken extends GameEntity {

	private final static Image[] spriteSources = {new Image(Chicken.class.getResourceAsStream("Chicken.png")), 
												  new Image(Chicken.class.getResourceAsStream("Chicken_left.png"))};
	private static final int MAX_FIRE_COOLDOWN = Game.FPS / 2;
	
	private final GameInput input;
	
	private final double speed = 15;
	private int fireCooldown = 0;
	private int direction = 1;
	
	public Chicken(GameInput input) {
		super(new Sprite(spriteSources[0]));
		this.input = input;
	}
        
        @Override
	public String getName() {
		return "Chicken";
	}

        @Override
	public void update(Game game) {
		move(game);
		fire(game);
		
		Set<GameEntity> colitions = game.collidingWith(this);
		collide(colitions, game);
	}
	
	private void move(Game game) {
		double speedX = 0, speedY = 0;
		double lX = getX(), lY = getY();

		if (input.isActive(GameInput.ACTION_LEFT)) {
			speedX = -speed;
			setSprite(new Sprite(spriteSources[1]));
			setXY(lX, lY);
			direction = -1;
		} else if (input.isActive(GameInput.ACTION_RIGHT)) {
			speedX = speed;
			setSprite(new Sprite(spriteSources[0]));
			setXY(lX, lY);
			direction = 1;
		}

		setXY(getX() + speedX, getY() + speedY);
		
		//Constrain the chicken inside the window
		if (getX() > (game.getWidth() - getWidth())) {
			setX(game.getWidth() - getWidth());
		}
		if (getX() < 0) {
			setX(0);
		}
		if (getY() > (game.getHeight() - getHeight())) {
			setY(game.getHeight() - getHeight());
		}
		if (getY() < 0) {
			setY(0);
		}

	}
	
	private void fire(Game game) {
		if (input.isActive(GameInput.ACTION_FIRE) && fireCooldown == 0) {
			fireCooldown = MAX_FIRE_COOLDOWN;

			// We spawn the new bullet centered on our ship, above it and separated by 5px
			Egg egg = new Egg();
			
			if (direction == 1) {
				egg.setXY(
						getX() + getWidth() - egg.getWidth()/2,
						getY());				
			} else {
				egg.setXY(
						getX() - egg.getWidth()/2,
						getY());	
			}
			

			game.addEntity(egg);
		}

		if (fireCooldown > 0) {
			--fireCooldown;
		}
	}
	
	public boolean collidesWith (GameEntity other) {
		if (other.getName() == "Cat") {
			return (checkCollision(other));
		}else {return false;}
	}

	public boolean checkCollision(GameEntity other) {
		final Rectangle otherBox = other.getCollisionBox();
		final Rectangle myBox = getCollisionBox();
		return otherBox.intersects(myBox.getBoundsInParent());
	}
           
	private void collide(Set<GameEntity> colitions, Game game) {
		if (!colitions.isEmpty()){
			game.gameOver();
			game.removeEntity(this);
		}
	}
}
