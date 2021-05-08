package launcher;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import game.Chicken;
import game.Game;
import javafx.geometry.Pos;

public class Launcher extends Application {
	
	private Canvas canvas;
	private Game game;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//Start the application
	@Override
	public void start(Stage stage) throws Exception {
		//Create a new window
		VBox root = new VBox();
                
                canvas = new Canvas(600, 550);
		root.getChildren().add(canvas);
                
                
		Text text = new Text();
		text.setFont(new Font(40));
		text.setTextAlignment(TextAlignment.JUSTIFY);
		text.setText("");
		text.setFill(Color.WHITE);
		root.getChildren().add(text);
                root.setAlignment(Pos.CENTER);
		



		Scene scene = new Scene(root);
		scene.setFill(Color.PURPLE);
		stage.setResizable(false);
		stage.setTitle("Flying Chicken");
		stage.setScene(scene);
		stage.show();

		game = new Game(canvas);
		game.draw();
		//Set the game to update and draw itself a number of times per second
		Timeline drawEverySecond = new Timeline(
			new KeyFrame(Duration.millis(1000/Game.FPS), (event) -> {
				game.update();
				game.draw();
				text.setText(Integer.toString(game.getScore()));
			})
		);
		drawEverySecond.setCycleCount(Timeline.INDEFINITE);
		drawEverySecond.play();
	}

}
