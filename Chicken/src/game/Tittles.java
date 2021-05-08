package game;

import game.GameEntity;
import game.Sprite;
import game.Game;
import javafx.scene.image.Image;

public class Tittles extends GameEntity {

	private final static Image[] spriteSources = {
												  new Image(Chicken.class.getResourceAsStream("lvl1.png")), 
												  new Image(Chicken.class.getResourceAsStream("lvl2.png")), 
												  new Image(Chicken.class.getResourceAsStream("lvl3.png")), 
												  new Image(Chicken.class.getResourceAsStream("GameOver.png"))};
	private static final int DISPLAY_TIME = Game.FPS * 2;
	private int time_left = DISPLAY_TIME;
	private int reduce = 1;
	private int lvl;
	
	public Tittles(int lvl) {
		super(new Sprite(spriteSources[lvl]));
		if (lvl == spriteSources.length - 1) {
			reduce = 0;
		}
		this.lvl = lvl;
	}

	@Override
	public String getName() {
		return "Tittle card";
	}

	@Override
	public void update(Game game) {
		time_left -= reduce;
		if (time_left < 0){
			game.removeEntity(this);
		}
	}

	
}
