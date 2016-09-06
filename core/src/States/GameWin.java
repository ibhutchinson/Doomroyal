package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.game.DoomRoyal;

public class GameWin extends State {
	private Texture background;
	private FreeTypeFontParameter parameter;
	private FreeTypeFontGenerator gen;
	private BitmapFont font2;
	private Music gameWin;
    /*
     * The GameWin method is the constructor for the GameWin class.
     * 
     * @param GameStateManager gsm
     */
	public GameWin(GameStateManager gsm) {
		super(gsm);
		background = new Texture(Gdx.files.internal("missionSuc.jpg"));
		font2 = new BitmapFont();
		gen = new FreeTypeFontGenerator(Gdx.files.internal("WarnockPro-Regular.otf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		font2 = gen.generateFont(parameter);
		font2.setColor(Color.WHITE);
		gameWin = Gdx.audio.newMusic(Gdx.files.internal("gamewin.mp3"));
		gameWin.play();
	}
    /*
     * (non-Javadoc)
     * @see States.State#handleInput()
     */
	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			gameWin.stop();
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
		sb.draw(background, 0, 0, DoomRoyal.WIDTH, DoomRoyal.HEIGHT);
		font2.draw(sb, "Score: " + String.valueOf(Game.playerScore), 20, 180);
		font2.draw(sb, "Surviving Civilians: " + String.valueOf(Game.survivingCivilians), 20, 160);
		font2.draw(sb, "Final Score: " + String.valueOf(Game.playerScore * Game.survivingCivilians), 20, 140);

		sb.end();

	}
    /*
     * (non-Javadoc)
     * @see States.State#dispose()
     */
	@Override
	public void dispose() {
		background.dispose();
		gen.dispose();
		font2.dispose();
		gameWin.dispose();

	}

}
