package Functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	protected Vector2 position;
	protected Vector2 velocity;
	protected TextureAtlas walkAtlas;
	protected Texture texture;
	protected TextureAtlas attackAtlas;
	protected Animation animation;
	protected int health;
	protected int dmg;
	protected boolean isDead;
	protected float elapsedTime;

	/*
	 * The entity method is the constructor for the abstract class Entity.
	 * Entitys are every object in the game.
	 */
	public Entity() {
		isDead = false;

	}

	/*
	 * The update method updates the current state of the entity.
	 * 
	 * @param float delta
	 */
	public abstract void update(float delta);

	/*
	 * The render method handles the rendering of the animations and textures of
	 * the entity.
	 * 
	 * @param SpriteBatch sb
	 */
	public abstract void render(SpriteBatch sb);

	/*
	 * The hits method is the collision detection between entity objects.
	 * Detection is based on if the two entity objects rectangles contain each
	 * other.
	 * 
	 * @param Entity entity
	 * 
	 * @return true if objects contain one another and false if they do not
	 * contain each other
	 */
	public boolean hits(Entity entity) {
		return (entity.getBounds().contains((this.getBounds())));
	}
    /*
     * The method getBounds creates a rectangle around the Entity for collision detection.
     * @return Entity hit box
     */
	public Rectangle getBounds() {

		return new Rectangle(this.position.x, this.position.y, texture.getWidth(), texture.getHeight());
	}

	/**
	 * @return the elapsedTime
	 */
	public float getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * @param elapsedTime
	 *            the elapsedTime to set
	 */
	public void setElapsedTime(float elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture
	 *            the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

    /*
     * This method sets the position of the Entity.
     * @param New Entity Position Vector2
     */
	public void setPosition(Vector2 _newPosition) {
		this.position = _newPosition;
	}
    /*
     * This method get the position of the Entity.
     * @return Current Entity position Vector2
     */
	public Vector2 getPositon() {
		return this.position;
	}

	/**
	 * @return the velocity
	 */
	public Vector2 getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 *            the velocity to set
	 */
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the dmg
	 */
	public int getDmg() {
		return dmg;
	}

	/**
	 * @param dmg
	 *            the dmg to set
	 */
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	/**
	 * @return the isDead
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * @param isDead
	 *            the isDead to set
	 */
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	/**
	 * @return the walkAtlas
	 */
	public TextureAtlas getWalkAtlas() {
		return walkAtlas;
	}

	/**
	 * @param walkAtlas
	 *            the walkAtlas to set
	 */
	public void setWalkAtlas(TextureAtlas walkAtlas) {
		this.walkAtlas = walkAtlas;
	}

	/**
	 * @return the attackAtlas
	 */
	public TextureAtlas getAttackAtlas() {
		return attackAtlas;
	}

	/**
	 * @param attackAtlas
	 *            the attackAtlas to set
	 */
	public void setAttackAtlas(TextureAtlas attackAtlas) {
		this.attackAtlas = attackAtlas;
	}

	/**
	 * @return the animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * @param animation
	 *            the animation to set
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

}
