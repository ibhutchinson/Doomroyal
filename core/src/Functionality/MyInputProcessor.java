package Functionality;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor extends InputAdapter{
    
    /*
     * 		(non-Javadoc)
     * @see com.badlogic.gdx.InputAdapter#keyDown(int)
     */
    public boolean keyDown(int k){
    	if(k == Keys.W){
    		GameKeys.setKey(GameKeys.W, true);
    	}
    	if(k == Keys.S){
    		GameKeys.setKey(GameKeys.S, true);
    	}
    	if(k == Keys.A){
    		GameKeys.setKey(GameKeys.A, true);
    	}
    	if(k == Keys.D){
    		GameKeys.setKey(GameKeys.D, true);
    	}
    	if(k == Keys.SPACE){
    		GameKeys.setKey(GameKeys.SPACE, true);
    	}
    	return true;
    }
    /*
     * (non-Javadoc)
     * @see com.badlogic.gdx.InputAdapter#keyUp(int)
     */
    public boolean keyUp(int k){
    	if(k == Keys.W){
    		GameKeys.setKey(GameKeys.W, false);
    	}
    	if(k == Keys.S){
    		GameKeys.setKey(GameKeys.S, false);
    	}
    	if(k == Keys.A){
    		GameKeys.setKey(GameKeys.A, false);
    	}
    	if(k == Keys.D){
    		GameKeys.setKey(GameKeys.D, false);
    	}
    	if(k == Keys.SPACE){
    		GameKeys.setKey(GameKeys.SPACE, false);
    	}
    	return true;
    }

}
