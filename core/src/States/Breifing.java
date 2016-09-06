package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.DoomRoyal;

import Functionality.Boss;

public class Breifing extends State {
	private Texture bg;
	private Music soundMarine;
	private Music audio;
	
	public static Music backgroundNoise;
	
    /*
     * The Briefing method is the constructor for the Briefing class.
     * 
     * @param GameStateManager gsm
     */
	public Breifing(GameStateManager gsm) {
		super(gsm);
		bg = new Texture(Gdx.files.internal("breifingbg.PNG"));
		soundMarine = Gdx.audio.newMusic(Gdx.files.internal("gogogo.mp3"));
		backgroundNoise = Gdx.audio.newMusic(Gdx.files.internal("backgroundNoise.mp3"));
		audio = Gdx.audio.newMusic(Gdx.files.internal("breifingAudio.mp3"));
		Boss.bossHp = 50000;
		audio.play();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#handleInput()
	 */
	@Override
	protected void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.TAB)) {
			gsm.set(new Game(gsm));
			audio.stop();
			// audio.dispose();
			soundMarine.play();
			backgroundNoise.setVolume(0.5f);
			backgroundNoise.play();

		}
		if (audio.isPlaying() == false) {
			gsm.set(new Game(gsm));
			audio.stop();
			// audio.dispose();
			soundMarine.play();
			backgroundNoise.setVolume(0.5f);
			backgroundNoise.play();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#update(float)
	 */
	@Override
	public void update(float dt) {
		handleInput();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(bg, 0, 0, DoomRoyal.WIDTH, DoomRoyal.HEIGHT);
		sb.end();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#dispose()
	 */
	@Override
	public void dispose() {
		bg.dispose();
		soundMarine.dispose();
		audio.dispose();

	}

}
