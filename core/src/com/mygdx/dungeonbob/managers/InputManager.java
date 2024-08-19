package com.mygdx.dungeonbob.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by claud on 18/05/2018.
 */

public class InputManager extends InputAdapter
{

    @Override
    public boolean keyDown (int keycode)
    {
        if (keycode == Input.Keys.LEFT)
        {
            GameManager.bob.setLeftPressed(true);
        }
        else if (keycode == Input.Keys.RIGHT)
        {
            GameManager.bob.setRightPressed(true);
        }

        return false;
    }

    @Override
    public boolean keyUp (int keycode)
    {
        if(keycode == Input.Keys.LEFT)
        {
            GameManager.bob.setLeftPressed(false);
        }
        else if (keycode == Input.Keys.RIGHT)
        {
            GameManager.bob.setRightPressed(false);
        }

        return false;
    }

    boolean isLeftPaddleTouched(float touchX, float touchY){
        // handle touch input on the left paddle
        if((touchX>=GameManager.leftPaddleSprite.getX()) && touchX<=(GameManager.leftPaddleSprite.getX()+GameManager.leftPaddleSprite.getWidth()) && (touchY>=GameManager.leftPaddleSprite.getY()) && touchY<=(GameManager.leftPaddleSprite.getY()+GameManager.leftPaddleSprite.getHeight()) ){
            return true;
        }
        return false;
    }

    boolean isRightPaddleTouched(float touchX, float touchY){
        // handle touch input on the right paddle
        if((touchX>=GameManager.rightPaddleSprite.getX()) && touchX<=(GameManager.rightPaddleSprite.getX()+GameManager.rightPaddleSprite.getWidth()) && (touchY>=GameManager.rightPaddleSprite.getY()) && touchY<=(GameManager.rightPaddleSprite.getY()+GameManager.rightPaddleSprite.getHeight()) ){
            return true;
        }
        return false;
    }

    OrthographicCamera camera;
    static Vector3 temp = new Vector3(); // temporary vector
    public InputManager(OrthographicCamera camera) {

        this.camera = camera;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX,screenY, 0);
        //get the touch co-ordinates with respect to the camera's viewport
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        if(isLeftPaddleTouched(touchX,touchY)){
            GameManager.bob.setLeftPaddleTouched(true);
        }
        else if(isRightPaddleTouched(touchX,touchY)){
            GameManager.bob.setRightPaddleTouched(true);
        }
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX,screenY, 0);
        //get the touch co-ordinates with respect to the camera's viewport
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        if(isLeftPaddleTouched(touchX,touchY)){
            GameManager.bob.setLeftPaddleTouched(false);
        }
        else if(isRightPaddleTouched(touchX,touchY)){
            GameManager.bob.setRightPaddleTouched(false);
        }
        return false;
    }






}
