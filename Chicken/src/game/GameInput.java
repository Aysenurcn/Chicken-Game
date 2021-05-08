package game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class GameInput {

	public static final int ACTION_UP = 0;


	public static final int ACTION_DOWN = ACTION_UP + 1;


	public static final int ACTION_LEFT = ACTION_DOWN + 1;


	public static final int ACTION_RIGHT = ACTION_LEFT + 1;


	public static final int ACTION_FIRE = ACTION_RIGHT + 1;
	
	public static final int ACTION_RETRY = ACTION_FIRE + 1;

	private static final Map<KeyCombination, Integer> KEY_TO_ACTION = new HashMap<>();

	static {
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.W), ACTION_UP);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.A), ACTION_LEFT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.S), ACTION_DOWN);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.D), ACTION_RIGHT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.UP), ACTION_UP);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.LEFT), ACTION_LEFT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.DOWN), ACTION_DOWN);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.RIGHT), ACTION_RIGHT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_UP), ACTION_UP);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_LEFT), ACTION_LEFT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_DOWN), ACTION_DOWN);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.KP_RIGHT), ACTION_RIGHT);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.SPACE), ACTION_FIRE);
		KEY_TO_ACTION.put(new KeyCodeCombination(KeyCode.R), ACTION_RETRY);
	}
	
	private final Map<Integer, Boolean> actionActive = new HashMap<>();

	public GameInput(Scene scene) {

		class KeyMatchHandler implements EventHandler<KeyEvent> {
			
			private final boolean pressed;
			
			private KeyMatchHandler (Boolean p) {
				this.pressed = p;
			} 
			
			@Override
			public void handle(KeyEvent ev) {
				for (Entry<KeyCombination, Integer> entry : KEY_TO_ACTION.entrySet()) {
					final KeyCombination kc = entry.getKey();
					final int action = entry.getValue();
					if (kc.match(ev)) {
						actionActive.put(action, pressed);
						break;
					}
				}
			}
		}
		
		scene.setOnKeyPressed(new KeyMatchHandler(true));
		scene.setOnKeyReleased(new KeyMatchHandler(false));
	}

	public boolean isActive(int action) {
		return actionActive.getOrDefault(action, false);
	}
}
