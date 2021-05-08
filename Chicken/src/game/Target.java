package game;

import game.GameEntity;
import game.GameEntity;
import game.Sprite;
import game.Game;
import javafx.scene.image.Image;

public class Target extends GameEntity {
	private final static Image[] spriteSources = {new Image(Chicken.class.getResourceAsStream("target.png")), 
            									  new Image(Chicken.class.getResourceAsStream("target_med.png")), 
            									  new Image(Chicken.class.getResourceAsStream("target_small.png"))};

	private final double speed = 10;
	private int value = 0;
	
	public Target(int value) {
		super(new Sprite(spriteSources[0]));
		if (value < spriteSources.length) {
			setSprite(new Sprite(spriteSources[value]));
			this.value = value;
		}
	}

	@Override
	public String getName() {
		return "Target";
	}

	@Override
	public void update(Game game) {
		move(game);
		
	}
	
	private void move(Game game) {
		setXY(getX(), getY() - speed);
		
		if (getY() < -getHeight()) {
			game.removeEntity(this);
		}
	}

	@Override
	public boolean collidesWith (GameEntity other) {
		if (other.getName() == "Egg") {
			return (super.collidesWith(other));
		}else {return false;}
	}

	public int getScore() {
		return value;
	}
}
