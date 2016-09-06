package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import States.GameStateManager;
import States.MenuState;


public class DoomRoyal extends ApplicationAdapter {
	private SpriteBatch batch;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 450;
	private GameStateManager gsm;

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#create()
	 */
	@Override
	public void create () {
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		gsm.push(new MenuState(gsm));
		
	}
    /*
     * (non-Javadoc)
     * @see com.badlogic.gdx.ApplicationAdapter#render()
     */
	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
