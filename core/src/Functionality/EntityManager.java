package Functionality;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.mygdx.game.DoomRoyal;

import States.Game;

public class EntityManager {
	private ArrayList<Entity> manageList;
	private ArrayList<Entity> trashCan;
	private Queue<Entity> loader;
	private Music hit;
	private BossBullet bulletB;
	private Music missleS;
	private Music missleH;
	private Music deathSound;
	private int bossCount;
	private Rectangle2D playerBox;
	private Rectangle2D bossMissleBox;
	private boolean death;

	/*
	 * The method EntityManager is the constructor for the EnityManager. The
	 * EntityManage manages most of the interaction that the entity's in the
	 * game have.
	 */
	public EntityManager() {
		loader = new Queue<Entity>();
		manageList = new ArrayList<Entity>();
		trashCan = new ArrayList<Entity>();
		hit = Gdx.audio.newMusic(Gdx.files.internal("hit.wav"));
		missleS = Gdx.audio.newMusic(Gdx.files.internal("mShot.mp3"));
		missleH = Gdx.audio.newMusic(Gdx.files.internal("mExp.mp3"));
		deathSound = Gdx.audio.newMusic(Gdx.files.internal("cyberdeath.mp3"));
		death = false;

	}

	/*
	 * The method update handles all the updates across the individual entity
	 * updates. This method also handles assets management in the game. It adds
	 * Entity's into a queue then the queue loads assets into the manageList
	 * where the method checks the status of the entity and determines if it
	 * needs to be added to the trashcan. This method also updates the demon and
	 * boss Ai and updates collision detection.
	 * 
	 * @param float delta
	 */
	public void update(float delta) {
		demonAi();
		bossAi();
		for (Entity e : loader) {
			manageList.add(e);
		}
		loader.clear();
		for (Entity e : manageList) {
			if (e.isDead()) {
				trashCan.add(e);
			} else {
				e.update(delta);
			}
			if (e.getPositon().x > DoomRoyal.WIDTH) {
				trashCan.add(e);
			}
			if (e.getPositon().x < -5) {
				trashCan.add(e);
			}
		}
		for (Entity e : trashCan) {
			manageList.remove(e);
		}

		trashCan.clear();
		this.checkCollision();

	}

	/*
	 * The render method handles all the individual entities render methods.
	 * 
	 * @param SpriteBatch sb
	 */
	public void render(SpriteBatch sb) {
		for (Entity e : manageList) {
			e.render(sb);
		}
	}
    /*
     * The checkCollision method checks for collision between all objects in the game.
     */
	public void checkCollision() {
		for (Bullet bullet : getBullets()) {
			for (Demons demon : getDemons()) {
				for (Player player : getPlayer()) {
					if (bullet.hits(demon) && demon.getPositon().x > player.getPositon().x) {
						hit.play();
						demon.setHealth(demon.getHealth() - player.getDmg());
						if (demon.getHealth() < 0) {
							Game.playerScore += 100;
							trashCan.add(demon);
						}
					}
				}
			}
		}
		for (Bullet b : getBullets()) {
			for (Boss boss : getBoss()) {

				if (b.hits(boss)) {
					hit.play();
					boss.setHealth(boss.getHealth() - 1);
				}

			}

		}

		for (BossBullet bB : getBossBullet()) {
			for (Player p : getPlayer()) {
				bossMissleBox = new Rectangle((int) bB.getPositon().x, (int) bB.getPositon().y,
						bB.getTexture().getWidth() - 15, bB.getTexture().getHeight() - 15);
				playerBox = new Rectangle((int) p.getPositon().x, (int) p.getPositon().y, 61, 51);
				if (bossMissleBox.intersects(playerBox)) {
					bB.setPosition(new Vector2(bB.getPositon().x, bB.getPositon().y));
					bB.setTexture(new Texture(Gdx.files.internal("boom.png")));
					missleH.play();
					Player.playerHealth -= 5;
					bB.setDead(true);
				}
			}
		}
	}
    /*
     * The method demonAi handles the moment of the demons.
     */
	public void demonAi() {
		for (Player p : getPlayer()) {
			for (Demons d : getDemons()) {
				if (!death) {

					if (d.getPositon().x > p.getPositon().x) {
						d.setPosition(new Vector2(d.getPositon().x - 1.3f, d.getPositon().y));
					}
					if (d.getPositon().x < p.getPositon().x) {
						d.setPosition(new Vector2(d.getPositon().x - 1.3f, d.getPositon().y));
					}
					if (d.getPositon().x < 0) {
						trashCan.add(d);
						Game.playerScore -= 50;
						Game.survivingCivilians--;
					}
				}
			}
		}
	}
    /*
     * The method bossAt handles the movement and shooting of the boss.
     */
	public void bossAi() {
		int shootB = ThreadLocalRandom.current().nextInt(0, 50);

		for (Player p : getPlayer()) {
			for (Boss b : getBoss()) {
                if(!death){
				if (b.getPositon().x > 700) {
					b.setPosition(new Vector2(b.getPositon().x - 1, b.getPositon().y));

				}
				if (p.getPositon().y-10 > b.getPositon().y) {
					b.setPosition(new Vector2(b.getPositon().x, b.getPositon().y + 0.6f));

				}
				if (p.getPositon().y-20 < b.getPositon().y) {
					b.setPosition(new Vector2(b.getPositon().x, b.getPositon().y - 0.6f));

				}
				if (b.getIncounterTimer() == 100) {

					bulletB = new BossBullet(new Vector2(b.getPositon().x - 6, b.getPositon().y + 40),
							new Vector2(600, 0), 5, new Texture(Gdx.files.internal("missle.png")), false);
					b.setAnimation(new Animation(1f / 3f, b.getAttackAtlas().getRegions()));
					missleS.play();
					this.manageList.add(bulletB);

				}
				if (b.getIncounterTimer() == 150) {
					b.setAnimation(new Animation(1f / 3f, b.getWalkAtlas().getRegions()));
					b.setIncounterTimer(0);
				}
				if (b.getHealth() < 0) {
					b.setIncounterTimer(400);
					if (death != true) {
						deathSound.play();
						b.setAnimation(new Animation(1f / 3f,
								new TextureAtlas(Gdx.files.internal("CyberDemon/Death.atlas")).getRegions()));
						death = true;
					}
					if (death) {
						b.setAnimation(new Animation(1f / 3f,
								new TextureAtlas(Gdx.files.internal("CyberDemon/Death.atlas")).getRegions().get(8)));
					}
				}
				}
			}
		}

	}
    /*
     * The method getBullets separates the bullets from the manageList for collision detection.
     * 
     * @return ArrayList of bullets.
     */
	public ArrayList<Bullet> getBullets() {
		ArrayList<Bullet> b = new ArrayList();
		for (Entity e : manageList) {
			if (e instanceof Bullet) {
				b.add((Bullet) e);
			}
		}
		return b;
	}
    /*
     * The method getDemons separates the demons from the manageList for collision detection.
     * 
     * @return ArrayList of demons.
     */
	public ArrayList<Demons> getDemons() {
		ArrayList<Demons> d = new ArrayList();
		for (Entity e : manageList) {
			if (e instanceof Demons) {
				d.add((Demons) e);
			}
		}
		return d;
	}
    /*
     * The method getPlayer separates the player from the manageList for collision detection.
     * 
     * @return ArrayList of player.
     */
	public ArrayList<Player> getPlayer() {
		ArrayList<Player> p = new ArrayList();
		for (Entity e : manageList) {
			if (e instanceof Player) {
				p.add((Player) e);
			}
		}
		return p;
	}
    /*
     * The method getBoss separates the demons from the manageList for collision detection.
     * 
     * @return ArrayList of demons.
     */
	public ArrayList<Boss> getBoss() {
		ArrayList<Boss> b = new ArrayList();
		for (Entity e : manageList) {
			if (e instanceof Boss) {
				b.add((Boss) e);
			}

		}
		return b;
	}
    /*
     * The method getBossBullet separates the boss bullets from the manageList for collision detection.
     * 
     * @return ArrayList of Boss Bullets.
     */
	public ArrayList<BossBullet> getBossBullet() {
		ArrayList<BossBullet> bB = new ArrayList();
		for (Entity e : manageList) {
			if (e instanceof BossBullet) {
				bB.add((BossBullet) e);
			}
		}
		return bB;
	}
    /*
     * The method addEntity adds Entity's to the loader queue.
     * 
     * @param Entity entity
     */
	public void addEntity(Entity entity) {
		loader.addLast(entity);
	}

}
