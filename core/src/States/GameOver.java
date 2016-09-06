package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DoomRoyal;

public class GameOver extends State {
	private Texture background;
	private Music gameLose;
    /*
     * The GameOver method is the constructor for the GameOver class
     * 
     * @param GameStateManager gsm
     */
	public GameOver(GameStateManager gsm) {
		super(gsm);
		background = new Texture(Gdx.files.internal("missionFail.jpg"));
		gameLose = Gdx.audio.newMusic(Gdx.files.internal("gamelose.mp3"));
		gameLose.play();
	}
    /*
     * (non-Javadoc)
     * @see States.State#handleInput()
     */
	@Override
	protected void handleInput() {
		if(Gdx.input.isKeyJustPressed(Keys.M))
		{
			gameLose.stop();
			gsm.set(new MenuState(gsm));
			dispose();
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
		sb.draw(background,0,0,DoomRoyal.WIDTH,DoomRoyal.HEIGHT);
		sb.end();

	}
    /*
     * (non-Javadoc)
     * @see States.State#dispose()
     */
	@Override
	public void dispose() {
		background.dispose();
		gameLose.dispose();

	}

}
