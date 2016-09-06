package Functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity{
  
    /*
     * The bullet method is the constructor for the bullet class.
     * 
     * @param sentLocation The location where the bullet is currently at.
     * @param sentVelocity The speed that the bullet moves across the screen.
     * @param dmg The damage the the bullet does.
     * @param bullet The Texture of the bullet
     * @param isDead Is the bullet
     */
    public Bullet(Vector2 sentLocation, Vector2 sentVelocity,int dmg,Texture bullet,boolean isDead){
    	this.texture = new Texture(Gdx.files.internal("bullet.png"));
    	this.position = new Vector2(sentLocation.x,sentLocation.y);
    	this.velocity = new Vector2(sentVelocity.x,sentVelocity.y);
    	this.isDead = isDead;
    	
    }
    /*
     * (non-Javadoc)
     * @see Functionality.Entity#update(float)
     */
	@Override
	public void update(float delta) {
    	this.getPositon().x+= this.getVelocity().x *delta;
    	this.getPositon().y+= this.getVelocity().y *delta;
	}
    /*
     * (non-Javadoc)
     * @see Functionality.Entity#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
     */
	@Override
	public void render(SpriteBatch sb) {
      sb.draw(texture,this.position.x,this.position.y);
		
	}

}
