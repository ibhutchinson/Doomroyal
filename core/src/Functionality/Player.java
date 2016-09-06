package Functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	private Bullet bullet;
	private EntityManager playerManager;
	public static int playerHealth;

	/*
	 * The player method is the constructor for the player class.
	 * 
	 * @param _playerHp The players health points.
	 * 
	 * @param _playerDPS The players damage.
	 * 
	 * @param _walkAtlas The TextureAtlas for the players walking animation.
	 * 
	 * @param _attack The TextureAtlas for the players attacking animation.
	 * 
	 * @param _playerLocation The current location of the player in the game.
	 * 
	 * @param _animation The animation of the current TextureAtlas of the
	 * player.
	 * 
	 * @param _manager The link to the EntityManager
	 */
	public Player(int _playerHp, int _playerDPS, TextureAtlas _walkAtlas, TextureAtlas _attack, Vector2 _playerLocation,
			Animation _animation, EntityManager _manager) {
		this.health = _playerHp;
		this.dmg = _playerDPS;
		this.animation = _animation;
		this.walkAtlas = _walkAtlas;
		this.attackAtlas = _attack;
		this.position = _playerLocation;
		playerManager = _manager;
		this.elapsedTime = 0f;
		playerHealth = this.getHealth();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Functionality.Entity#update(float)
	 */
	@Override
	public void update(float delta) {
		input();

	}

	/*
	 * The input method handles the input for the movement of the player and
	 * adding bullets while shooting
	 */
	public void input() {
		if (GameKeys.isDown(GameKeys.W)) {
			if (this.getPositon().y <= 400) {
				this.animation = new Animation(1f / 4f, this.walkAtlas.getRegions());
				this.position.y += 2;
			}
		}
		if (!(GameKeys.isDown(GameKeys.W))) {
			if (this.getPositon().y <= 400) {
				this.animation = new Animation(0f / 0f, this.walkAtlas.getRegions());

			}
		}
		if (GameKeys.isDown(GameKeys.S)) {
			if (this.getPositon().y > 0) {
				this.animation = new Animation(1f / 4f, this.walkAtlas.getRegions());
				this.position.y -= 2;
			}
		}

		if (GameKeys.isDown(GameKeys.A)) {

			this.animation = new Animation(1f / 4f, this.walkAtlas.getRegions());
			if (this.getPositon().x > 0) {
				this.position.x -= 2;
			}
		}
		if (GameKeys.isDown(GameKeys.D)) {

			this.animation = new Animation(1f / 4f, this.walkAtlas.getRegions());
			if (this.getPositon().x <= 743.0) {
				this.position.x += 2;
			}
		}
		if (GameKeys.isDown(GameKeys.SPACE)) {
			bullet = new Bullet(new Vector2(this.getPositon().x + 49, this.getPositon().y + 24), new Vector2(600, 0), 5,
					new Texture(Gdx.files.internal("bullet.png")), false);
			this.setAnimation(new Animation(1f / 8f, this.attackAtlas.getRegions()));
			playerManager.addEntity(bullet);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Functionality.Entity#getBounds()
	 */
	public Rectangle getBounds() {

		return new Rectangle(this.position.x, this.position.y, this.walkAtlas.getTextures().first().getWidth(),
				this.walkAtlas.getTextures().first().getHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * Functionality.Entity#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
	 */
	@Override
	public void render(SpriteBatch sb) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		sb.draw(this.animation.getKeyFrame(elapsedTime, true), this.position.x, this.position.y);
	}

}
