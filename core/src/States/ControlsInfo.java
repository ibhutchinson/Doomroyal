package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DoomRoyal;

public class ControlsInfo extends State {
	private Texture background;
    /*
     * The ContolsInfo is the Constructor for the ControlsInfo class
     * 
     * @param GameStateManager gsm
     */
	public ControlsInfo(GameStateManager gsm) {
		super(gsm);
		background = new Texture(Gdx.files.internal("Controlsbackground.jpg"));
		GameStateManager.sound.stop();
	}
    /*
     * (non-Javadoc)
     * @see States.State#handleInput()
     */
	@Override
	protected void handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			gsm.set(new MenuState(gsm));
		}

	}
    /*
     * (non-Javadoc)
     * @see States.State#update(float)
     */
	@Override
	public void update(float dt) {
		handleInput();

	}
    /*
     * (non-Javadoc)
     * @see States.State#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
     */
	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(background, 0, 0,DoomRoyal.WIDTH,DoomRoyal.HEIGHT);
		sb.end();

	}
    /*
     * (non-Javadoc)
     * @see States.State#dispose()
     */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
