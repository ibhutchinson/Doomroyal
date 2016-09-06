package States;

/*
 * States.java
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

	protected Vector3 mouse;
	protected GameStateManager gsm;
	protected long startTime;
	protected int time;
	protected int finalWinningScore;

	/*
	 * Constructor for Abstract State Class
	 * 
	 * @param GameStateManager gsm
	 */
	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		mouse = new Vector3();
		startTime = System.nanoTime();
	}

	/*
	 * The handleInput method takes all the logic for input in the game so that
	 * one call can be made in the update.
	 */
	protected abstract void handleInput();

	/*
	 * The update method updates all the content in the game.
	 * 
	 * @param float dt
	 */
	public abstract void update(float dt);

	/*
	 * The render method draws all the game content to the screen.
	 * 
	 * @param SpriteBatch sb
	 */
	public abstract void render(SpriteBatch sb);

	/*
	 * The dispose method takes away all the rendered objects to the screen when
	 * they are no longer being used to prevent memory leaks.
	 */
	public abstract void dispose();
}
