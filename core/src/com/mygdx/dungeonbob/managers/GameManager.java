package com.mygdx.dungeonbob.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dungeonbob.gameobjects.Bob;

/**
 * Created by claud on 18/05/2018.
 */

public class GameManager
{

    static Bob bob;
    static Texture bobSpriteSheet;
    public static Sprite backgroundSprite;
    public static Texture backgroundTexture;


    static Texture leftPaddleTexture;
    static Texture rightPaddleTexture;
    static Sprite leftPaddleSprite;
    static Sprite rightPaddleSprite;
    public static final float PADDLE_RESIZE_FACTOR = 700f;
    public static final float PADDLE_ALPHA = 0.25f;
    public static final float PADDLE_HORIZ_POSITION_FACTOR = 0.02f;
    public static final float PADDLE_VERT_POSITION_FACTOR = 0.01f;


    public static void initializeLeftPaddle(float width,float height){
        //load background texture
        leftPaddleTexture = new Texture(Gdx.files.internal("paddleLeft.png"));
        //set left paddle sprite with the texture
        leftPaddleSprite= new Sprite(leftPaddleTexture);
        // resize the sprite
        leftPaddleSprite.setSize(leftPaddleSprite.getWidth()*width/ PADDLE_RESIZE_FACTOR, leftPaddleSprite.getHeight()*width/ PADDLE_RESIZE_FACTOR);
        // set the position to bottom left corner with offset
        leftPaddleSprite.setPosition(width* PADDLE_HORIZ_POSITION_FACTOR, height* PADDLE_VERT_POSITION_FACTOR);
        // make the paddle semi transparent
        leftPaddleSprite.setAlpha(PADDLE_ALPHA);
    }

    public static void initializeRightPaddle(float width,float height) {
        //load background texture
        rightPaddleTexture = new Texture(Gdx.files.internal("paddleRight.png"));
        //set right paddle sprite with the texture
        rightPaddleSprite = new Sprite(rightPaddleTexture);
        // resize the sprite
        rightPaddleSprite.setSize(rightPaddleSprite.getWidth() * width / PADDLE_RESIZE_FACTOR, rightPaddleSprite.getHeight() * width / PADDLE_RESIZE_FACTOR);
        // set the position to bottom left corner with offset
        rightPaddleSprite.setPosition(leftPaddleSprite.getX() + leftPaddleSprite.getWidth() + width * PADDLE_HORIZ_POSITION_FACTOR, height * PADDLE_VERT_POSITION_FACTOR);
        // make the paddle semi transparent
        rightPaddleSprite.setAlpha(PADDLE_ALPHA);

    }
    public static void initialize (float width, float height)
    {
        bob = new Bob();
        bobSpriteSheet = new Texture("bob_spritesheet.png");
        bob.initialize(width,height,bobSpriteSheet);



        backgroundTexture = new Texture("background.jpg");
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setSize(width,height);

        initializeLeftPaddle(width,height);
        initializeRightPaddle(width,height);


    }

    public static void renderGame(SpriteBatch batch)
    {
        backgroundSprite.draw(batch);
        bob.update();
        bob.render(batch);

        leftPaddleSprite.draw(batch);
        rightPaddleSprite.draw(batch);

    }

    public static void dispose()
    {
        backgroundTexture.dispose();
        bobSpriteSheet.dispose();
    }












}
