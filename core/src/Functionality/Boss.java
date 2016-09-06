package Functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import States.GameWin;

public class Boss extends Entity {
	private Bullet bullet;
	private EntityManager bossManager;
	private int incounterTimer;
	private Music Roar;
	private FreeTypeFontParameter parameter;
	private FreeTypeFontGenerator gen;
	private BitmapFont font2;
	
	
	public static int bossHp;
	
	/*
	 * The method Boss is the constructor for the boss class.
	 * 
	 * @param _health The health of the boss
	 * @param _damage The damage of the boss
	 * @param _isDead Is the boss dead
	 * @param _postion The position of the boss
	 * @param _walking The TextureAtlas of the boss walking
	 * @param _attacking The TextureAtlas of the boss attacking
	 * @param _anmation The animation of the current TextureAtlas
	 * @param _bossManager The link to the EntityManager Class
	 */
	public Boss(int _health, int _damage, boolean _isDead, Vector2 _postion, TextureAtlas _walking,
			TextureAtlas _attacking, Animation _animation, EntityManager _bossManager) {
		 this.health = _health;
		 this.dmg = _damage;
		 this.isDead = _isDead;
		 this.position = _postion;
		 this.walkAtlas = _walking;
		 this.attackAtlas = _attacking;
		 this.animation =_animation;
		 bossManager = _bossManager;
		 elapsedTime = 0f;
			gen = new FreeTypeFontGenerator(Gdx.files.internal("WarnockPro-Regular.otf"));
			parameter = new FreeTypeFontParameter();
			parameter.size = 15;
			font2 = gen.generateFont(parameter);
			font2.setColor(Color.RED);
			
		 Roar = (Gdx.audio.newMusic(Gdx.files.internal("roar.mp3")));
		 Roar.play();
		 
	}
    /*
     * (non-Javadoc)
     * @see Functionality.Entity#update(float)
     */
	@Override
	public void update(float delta) {
		setIncounterTimer(getIncounterTimer() + 1);
		bossHp = this.getHealth();


	}
	/*
	 * (non-Javadoc)
	 * @see Functionality.Entity#getBounds()
	 */
	public Rectangle getBounds() {

		return new Rectangle(this.position.x, this.position.y, this.walkAtlas.getTextures().first().getWidth(),
				this.walkAtlas.getTextures().first().getHeight());
	}
    /*
     * (non-Javadoc)
     * @see Functionality.Entity#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
     */
	@Override
	public void render(SpriteBatch sb) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		sb.draw(this.animation.getKeyFrame(elapsedTime, true), this.position.x, this.position.y);
		font2.draw(sb, String.valueOf(this.health)+" / 20000",this.position.x,this.position.y+150);

	}
    /*
     * The getter method for the IncounterTimer
     * 
     * @return The incounterTimer 
     */
	public int getIncounterTimer() {
		return incounterTimer;
	}
    /*
     * The setter for the IncounterTimer.
     * 
     * param The new time of the IncounterTimer
     */
	public void setIncounterTimer(int incounterTimer) {
		this.incounterTimer = incounterTimer;
	}

}
