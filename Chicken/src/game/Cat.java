package game;


import game.GameEntity;
import game.Sprite;
import game.Game;
import javafx.scene.image.Image;

public class Cat extends GameEntity {

	private final static Image spriteSource = new Image(Chicken.class.getResourceAsStream("Cat.png"));
	
	private final double speed = 10;
	
	public Cat() {
		super(new Sprite(spriteSource));
	}

	@Override
	public String getName() {
		return "Cat";
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
}
