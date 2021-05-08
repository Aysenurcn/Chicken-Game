package game;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Random;
import java.time.LocalTime;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Game {

	public static final int FPS = 30;
	private static final int[] levels = {1,3,5};
	
	private final Canvas canvas;
	private final GameInput input;
	private final Random random;
	
	private Set<GameEntity> entities = new HashSet<>();
	private int level = 0;
	private int score = 0;
	private boolean playing = false;
	
	public Game(Canvas canvas) {
		this.random = new Random(LocalTime.now().toNanoOfDay());
		this.canvas = canvas;
		this.input = new GameInput(canvas.getScene());
		
		startGame();
	}

	public boolean addEntity(GameEntity entity) {
		return entities.add(entity);
	}

	public boolean removeEntity(GameEntity entity) {
		return entities.remove(entity);
	}

	public Set<GameEntity> collidingWith(GameEntity entity) {
		return entities.stream()
				.filter(e -> entity.collidesWith(e))
				.collect(Collectors.toSet());
	}

	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.VIOLET);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		entities.forEach(e -> e.draw(gc));
	}

	public void update() {
		int chance = random.nextInt(101);
		if (playing) {
			if(chance < levels[level]) {
				spawnCat();
			}
			if(chance < 2) {
				spawnTarget();
			}
		}else {
			if(input.isActive(GameInput.ACTION_RETRY)) {
				startGame();
			}
		}
		
		
		Set<GameEntity> en = new HashSet<>(entities);
		en.forEach(e -> e.update(this));
		
	}
	
	private void spawnPlayer() {
		Chicken chicken = new Chicken(input);
		addEntity(chicken);
		chicken.setXY(
			canvas.getWidth() / 2 - chicken.getWidth()/2,
			canvas.getHeight() / 4 - chicken.getHeight()/2);
	}
	
	private void spawnCat() {
		Cat cat = new Cat();
		addEntity(cat);
		double randomX = 0 + (random.nextDouble() * (canvas.getWidth() - cat.getWidth()));
		cat.setXY(
				randomX,
				canvas.getHeight());
	}
	
	private void spawnTarget() {
		Target target = new Target(random.nextInt(3));
		addEntity(target);
		double randomX = 0 + (random.nextDouble() * (canvas.getWidth() - target.getWidth()));
		target.setXY(
				randomX,
				canvas.getHeight());
	}
	
	private void showTittleCard(int tittle) {
		Tittles card = new Tittles(tittle);
		addEntity(card);
		card.setXY(((canvas.getWidth()/2) - (card.getWidth()/2)), ((canvas.getHeight()/2) - (card.getHeight())));
	}
	
	public double getWidth() {
		return canvas.getWidth();
	}
	
	public double getHeight() {
		return canvas.getHeight();
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int score) {
		this.score += score;
		
		switch (level) {
		case 1:
			if (this.score > 20) {
				level = 2;
				showTittleCard(level);
			}
		case 2:
			if (this.score > 50) {
				level = 3;
				showTittleCard(level);
			}
		}
		
	}
	
	public void gameOver() {
		playing = false;
		
		showTittleCard(3);
		
	}
	
	public void startGame() {
		
                score = 0;
                level = 0;
      
		
		entities.clear();
		
		spawnPlayer();
		
		showTittleCard(level);
		
		playing = true;
	}
}
