package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DoomRoyal;

public class MenuState extends State {
	private Texture background;

	/*
	 * The method MenuState is the constructor for the MenuState class.
	 * 
	 * 
	 * @param GameStateManger gsm.
	 */
	public MenuState(GameStateManager gsm) {
		super(gsm);
		background = new Texture(Gdx.files.internal("menubackground.jpg"));

		gsm.menuMusic();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#handleInput()
	 */
	protected void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			gsm.set(new Breifing(gsm));

			GameStateManager.sound.stop();
			dispose();

		}
		if (Gdx.input.isKeyJustPressed(Keys.C)) {
			gsm.set(new ControlsInfo(gsm));
		}
		if (Gdx.input.isKeyJustPressed(Keys.T)) {
			System.out.println("Hit");
			gsm.set(new GameWin(gsm));
			GameStateManager.sound.stop();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#update(float)
	 */
	public void update(float dt) {
		handleInput();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
	 */
	public void render(SpriteBatch sb) {

		sb.begin();
		sb.draw(background, 0, 0, DoomRoyal.WIDTH, DoomRoyal.HEIGHT);
		sb.end();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#dispose()
	 */
	public void dispose() {
		background.dispose();


	}

}
