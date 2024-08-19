package com.mygdx.dungeonbob.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



/**
 * Created by claud on 18/05/2018.
 */

public class Bob
{
    public static final float BOB_RESIZE_FACTOR = 400f;

    boolean isLeftPressed;
    boolean isRightPressed;

    private static final float X_MOVE_UNITS = 3f;

    public Sprite bobSprite;


    Animation walkAnimation;
    Texture walkSheet;
    TextureRegion currentFrame;
    float stateTime;

    private static int ANIMATION_FRAME_SIZE = 8;
    private static float ANIMATION_TIME_PERIOD = 0.08f;

    boolean updateAnimationStateTime = false; // keep track of when to update Bob's state time


    enum Direction{LEFT, RIGHT};
    Direction direction = Direction.RIGHT;


    boolean isLeftPaddleTouched;  // indicates if left paddle is touched
    boolean isRightPaddleTouched; // indicates if right paddle is touched

    public void render (SpriteBatch batch)
    {
        bobSprite.setRegion(currentFrame);

        if (direction == Direction.LEFT)
        {
            bobSprite.setFlip(true,false);
        }
        else
        {
            bobSprite.setFlip(false,false);
        }

        bobSprite.draw(batch);
    }

    public void setBobPosition(float x, float y)
    {
        bobSprite.setPosition(x,y);
    }

    public void move (float x, float y)
    {
        setBobPosition(bobSprite.getX()+x, bobSprite.getY()+y);
    }

    public void setLeftPressed (boolean isPressed)
    {
        if (isRightPressed && isPressed)
        {
            isRightPressed = false;
        }

        isLeftPressed = isPressed;
    }

    public void setRightPressed (boolean isPressed)
    {
        if (isLeftPressed && isPressed)
        {
            isLeftPressed = false;
        }

        isRightPressed = isPressed;
    }

    public void update()
    {

        updateAnimationStateTime = false;

        if (isLeftPressed)
        {
            updateAnimationStateTime = true;
            direction = Direction.LEFT;
            move(-X_MOVE_UNITS,0);
        }
        else if (isRightPressed)
        {
            updateAnimationStateTime = true;
            direction = Direction.RIGHT;
            move(X_MOVE_UNITS,0);
        }

        if (updateAnimationStateTime)
        {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime,true);
        }

        // move specified units to left if left paddle is touched
        if (isLeftPaddleTouched){
            move (-X_MOVE_UNITS,0);
            System.out.println("touched left");
        }

// move specified units to right if right paddle is touched
        else if (isRightPaddleTouched){
            move (X_MOVE_UNITS,0);
            System.out.println("touched left");
        }
    }

    public void initialize (float width, float height, Texture walkSheet)
    {
        this.walkSheet = walkSheet;

        //split the sprite sheet into different textures
        TextureRegion [] [] tmp = TextureRegion.split(walkSheet,walkSheet.getWidth()/ANIMATION_FRAME_SIZE,walkSheet.getHeight());
        //convert 2D array to 1D
        TextureRegion[] walkFrames = tmp[0];
        //create a new animation sequence with the walk frames and time period of specified seconds
        walkAnimation = new Animation(ANIMATION_TIME_PERIOD, walkFrames);




        bobSprite = new Sprite();
        bobSprite.setSize((walkSheet.getWidth()/ANIMATION_FRAME_SIZE)*(width/BOB_RESIZE_FACTOR),walkSheet.getHeight()*(width/BOB_RESIZE_FACTOR));
        setBobPosition(width/2f, 0);

        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime,true);



    }


    public void setLeftPaddleTouched(boolean isTouched)
    {
        // to restrict motion in only one direction if both are touched
        if(isRightPaddleTouched && isTouched){
            isRightPaddleTouched = false;
        }

        isLeftPaddleTouched = isTouched;
    }
    public void setRightPaddleTouched(boolean isTouched)
    {
        // to restrict motion if both are touched
        if(isLeftPaddleTouched && isTouched){
            isLeftPaddleTouched = false;
        }

        isRightPaddleTouched = isTouched;
    }




}
