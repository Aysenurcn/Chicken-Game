package game;

import java.util.Set;

import game.GameEntity;
import game.Sprite;
import game.Game;
import javafx.scene.image.Image;

public class Egg extends GameEntity {

	private final static Image spriteSource = new Image(Chicken.class.getResourceAsStream("Egg.png"));
	
	private final double speedY = 15;
	
	public Egg() {
		super(new Sprite(spriteSource));
	}

	@Override
	public String getName() {
		return "Egg";
	}

	@Override
	public void update(Game game) {
		move(game);
		
		Set<GameEntity> colitions = game.collidingWith(this);
		collide(colitions, game);
	}
	
	private void move(Game game) {
		setXY(getX(), getY() + speedY);
		
		if (getX() > game.getWidth() || getX() < -getWidth()) {
			game.removeEntity(this);
		}
	}
	
        @Override
	public boolean collidesWith (GameEntity other) {
		if (other.getName() == "Target") {
			return (super.checkCollision(other));
		}else {return false;}
	}

	private void collide(Set<GameEntity> colitions, Game game) {
		if (!colitions.isEmpty()){
			colitions.forEach(e -> {game.removeEntity(e); game.removeEntity(this);game.addScore(((Target) e).getScore()+1);});
		}
	}
}
