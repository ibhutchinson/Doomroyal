package States;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.DoomRoyal;

import Functionality.Boss;
import Functionality.Demons;
import Functionality.EntityManager;
import Functionality.GameKeys;
import Functionality.MyInputProcessor;
import Functionality.Player;

public class Game extends State {
	private Texture background;
	private EntityManager entityManager;
	private Music mGun;
	private Music bossSteps;
	private Music thisIsMy;
	private FreeTypeFontParameter parameter;
	private FreeTypeFontGenerator gen;
	private BitmapFont font2;
	private Demons demon;
	private Demons demon1;
	private Demons demon2;
	private Demons demon3;
	private Demons demon4;
	private Demons demon5;
	private Random rand;
	private Player player;
	private Boss boss;
	private boolean bossWave;
	private int waveCounter;
	private int wavesTillBoss;
	private boolean deathS;

	public static int deathTimeCount;
	public static int playerScore;
	public static int survivingCivilians;

	/*
	 * The game method is the constructor for the game state.
	 * 
	 * @param GameStateManager gsm. Allowing access to the state system.
	 */
	public Game(GameStateManager gsm) {
		super(gsm);
		font2 = new BitmapFont();
		gen = new FreeTypeFontGenerator(Gdx.files.internal("WarnockPro-Regular.otf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 30;
		font2 = gen.generateFont(parameter);
		font2.setColor(Color.RED);
		entityManager = new EntityManager();
		waveCounter = 0;
		playerScore = 0;
		deathTimeCount = 0;
		survivingCivilians = 10;
		wavesTillBoss = 0;
		bossWave = false;
		deathS = false;
		background = new Texture(Gdx.files.internal("gamemap.png"));
		mGun = Gdx.audio.newMusic(Gdx.files.internal("mGun.mp3"));
		bossSteps = Gdx.audio.newMusic(Gdx.files.internal("CyberDSteps.mp3"));
		thisIsMy = Gdx.audio.newMusic(Gdx.files.internal("this_is_my_rifle.mp3"));
		rand = new Random();
		player = new Player(50, 1, new TextureAtlas(Gdx.files.internal("StarCraftMarine/WR.atlas")),
				new TextureAtlas(Gdx.files.internal("StarCraftMarine/AR.atlas")), new Vector2(10, 10),
				new Animation(1f / 5f, (new TextureAtlas(Gdx.files.internal("StarCraftMarine/AR.atlas")).getRegions())),
				entityManager);
		entityManager.addEntity(player);
		GameStateManager.sound.stop();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#handleInput()
	 */
	@Override
	protected void handleInput() {
		Gdx.input.setInputProcessor(new MyInputProcessor());
		GameKeys.update();

		if (GameKeys.isDown(GameKeys.SPACE)) {
			mGun.play();
		}
		if (!(GameKeys.isDown(GameKeys.SPACE))) {
			mGun.stop();
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
		waveCounter++;
		wavesTillBoss++;
		if (deathS) {
			deathTimeCount++;
		}
		this.waveSpawner();
		this.bossFight();
		entityManager.update(dt);
		if (survivingCivilians < 0) {
			gsm.set(new GameOver(gsm));
			Breifing.backgroundNoise.stop();
			dispose();
		}
		if (Player.playerHealth == 0) {
			gsm.set(new GameOver(gsm));
			Breifing.backgroundNoise.stop();
			dispose();
		}
		if (Boss.bossHp < 0) {
			deathS = true;
			if (deathTimeCount == 150) {
				gsm.set(new GameWin(gsm));
				Breifing.backgroundNoise.stop();
				dispose();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(background, 0, 0, DoomRoyal.WIDTH, DoomRoyal.HEIGHT);

		entityManager.render(sb);
		font2.draw(sb, "Health Points: " + String.valueOf(Player.playerHealth), 0, 450);
		font2.draw(sb, "Score: " + String.valueOf(playerScore), 300, 450);
		font2.draw(sb, "Surviving Civilians:" + String.valueOf(survivingCivilians), 520, 450);
		sb.end();

	}

	/*
	 * The wave spawner method handles the timer for when enemy waves spawn. The
	 * waves are spawn on a counter with 5 demons in each wave and when the
	 * limit reaches five the waves stop then the boss spawns.
	 */
	public void waveSpawner() {

		if (waveCounter == 500 && bossWave != true) {
			demon = new Demons(1000, 1,
					new Animation(1f / 6f, (new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")).getRegions())),
					new Vector2(DoomRoyal.WIDTH, rand.nextInt(DoomRoyal.HEIGHT- 40) ),
					new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")),
					new TextureAtlas(Gdx.files.internal("Alien/AttackR.atlas")), false);

			demon1 = new Demons(1000, 1,
					new Animation(1f / 6f, (new TextureAtlas(Gdx.files.internal("Alien/walkL.atlas")).getRegions())),
					new Vector2(DoomRoyal.WIDTH, rand.nextInt(400)),
					new TextureAtlas(Gdx.files.internal("Alien/walkL.atlas")),
					new TextureAtlas(Gdx.files.internal("Alien/AttackR.atlas")), false);
			demon2 = new Demons(1000, 1,
					new Animation(1f / 6f, (new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")).getRegions())),
					new Vector2(DoomRoyal.WIDTH, rand.nextInt(400)),
					new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")),
					new TextureAtlas(Gdx.files.internal("Alien/AttackR.atlas")), false);
			demon3 = new Demons(1000, 1,
					new Animation(1f / 6f, (new TextureAtlas(Gdx.files.internal("Alien/walkL.atlas")).getRegions())),
					new Vector2(DoomRoyal.WIDTH, rand.nextInt(400)),
					new TextureAtlas(Gdx.files.internal("Alien/walkL.atlas")),
					new TextureAtlas(Gdx.files.internal("Alien/AttackR.atlas")), false);
			demon4 = new Demons(1000, 1,
					new Animation(1f / 6f, (new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")).getRegions())),
					new Vector2(DoomRoyal.WIDTH, rand.nextInt(400)),
					new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")),
					new TextureAtlas(Gdx.files.internal("Alien/AttackR.atlas")), false);
			demon5 = new Demons(1000, 1,
					new Animation(1f / 6f, (new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")).getRegions())),
					new Vector2(DoomRoyal.WIDTH, rand.nextInt(400)),
					new TextureAtlas(Gdx.files.internal("DoomDemon/WL.atlas")),
					new TextureAtlas(Gdx.files.internal("Alien/AttackR.atlas")), false);
			entityManager.addEntity(demon);
			entityManager.addEntity(demon1);
			entityManager.addEntity(demon2);
			entityManager.addEntity(demon3);
			entityManager.addEntity(demon4);
			entityManager.addEntity(demon5);
			waveCounter = 0;

		}

	}

	/*
	 * The bossFight method handles the events that lead up to the boss entering
	 * the room and then adds the boss.
	 */
	public void bossFight() {
		if (wavesTillBoss == 1500) {
			thisIsMy.play();
		}
		if (wavesTillBoss == 4000) {
			bossWave = true;
			bossSteps.setVolume(0.9f);
			bossSteps.play();
		}
		if (wavesTillBoss == 4500) {

			boss = new Boss(20000, 2, false, new Vector2(DoomRoyal.WIDTH, 200),
					new TextureAtlas(Gdx.files.internal("CyberDemon/WL.atlas")),
					new TextureAtlas(Gdx.files.internal("CyberDemon/attackL.atlas")),
					new Animation(1f / 3f, (new TextureAtlas(Gdx.files.internal("CyberDemon/WL.atlas")).getRegions())),
					entityManager);
			entityManager.addEntity(boss);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see States.State#dispose()
	 */
	@Override
	public void dispose() {
		background.dispose();
		font2.dispose();

	}

}
