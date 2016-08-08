package com.disc.jammers;

import com.disc.jammers.states.StatePlay;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.disc.jammers.states.StateDirector;
import com.disc.jammers.states.StateID;

public class DiscJammersMain extends ApplicationAdapter {

    private GameStateManager gsm;
    private SpriteBatch batch;

    private StateDirector stateDirector;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        stateDirector = new StateDirector(gsm);
        
        gsm.push(stateDirector.getState(StateID.PLAY));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    
    
}
