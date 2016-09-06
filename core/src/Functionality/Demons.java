package Functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Demons extends Entity {
    /*
     * The Demons method is the constructor for the demons class.
     * 
     * @param _demonHp The current health of the demon
     * @param _demonDps The current damage of the demon
     * @param _demonAnimation The animation of the demon
     * @param _demonLocation The current location of the demon in the game
     * @param _walkAtlas The walking TextureAtlas of the demon
     * @param _attack The attacking TextureAtlas of the demon
     * @param _isDead Is the demon dead.
     */
	public Demons(int _demonHp, int _demonDPS, Animation _demonAnimation, Vector2 _demonLocation,
			TextureAtlas _walkAtlas, TextureAtlas _attack, Boolean _isDead) {
		this.health = _demonHp;
		this.dmg = _demonDPS;
		this.animation = _demonAnimation;
		this.position = _demonLocation;
		this.attackAtlas = _attack;
		this.walkAtlas = _walkAtlas;
		this.isDead = _isDead;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Functionality.Entity#update(float)
	 */
	@Override
	public void update(float delta) {

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
     * @see Functionality.Entity#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
     */
	@Override
	public void render(SpriteBatch sb) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		sb.draw(this.animation.getKeyFrame(this.elapsedTime, true), this.position.x, this.position.y);

	}

}
