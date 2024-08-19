package com.mygdx.dungeonbob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.dungeonbob.managers.GameManager;
import com.mygdx.dungeonbob.managers.InputManager;

/**
 * Created by claud on 18/05/2018.
 */

public class GameScreen implements Screen
{

    MainGame game;
    SpriteBatch batch;
    OrthographicCamera camera;


    public GameScreen (MainGame game)
    {
        this.game = game;
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width,height);
        camera.setToOrtho(false);

        batch = new SpriteBatch();
        GameManager.initialize(width,height);

        Gdx.input.setInputProcessor(new InputManager(camera));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set the spritebatch's drawing view to the camera's view
        batch.setProjectionMatrix(camera.combined);

        // render the game objects
        batch.begin();
        GameManager.renderGame(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        GameManager.dispose();

    }
}
