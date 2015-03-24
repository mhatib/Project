package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SimpleButton {

    private float x, y, width, height;

    private TextureRegion buttonUp;
    private TextureRegion buttonDown;

    private boolean isPressed = false;
    private float screenHeight;
    
    public SimpleButton(float x, float y, float width, float height, float screenHeight){
    	this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.screenHeight = screenHeight;
    }

    public SimpleButton(float x, float y, float width, float height,
            TextureRegion buttonUp, TextureRegion buttonDown, float screenHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        this.screenHeight = screenHeight;

    }

    public void draw(ShapeRenderer sr, Color c) {
        if (isPressed) {
        	sr.begin(ShapeType.Filled);
        	sr.setColor(c);
        	sr.rect(x,y,width,height);
        	sr.end();
        } else {
        	sr.begin(ShapeType.Filled);
        	sr.setColor(Color.WHITE);
        	sr.rect(x,y,width,height);
        	sr.end();
        }
        
    }
    
    public void draw(SpriteBatch batcher) {
    	batcher.begin();
    	batcher.enableBlending();
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height);
        } else {
            batcher.draw(buttonUp, x, y, width, height);
        }
        batcher.end();
    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (isContained(screenX, screenHeight-screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {
        
        if (isContained(screenX, screenHeight-screenY)&& isPressed) {
            isPressed = false;
            return true;
        }
        
        isPressed = false;
        return false;
    }
    
    public boolean isContained(int screenX,float screenY){
    	if(screenX<=x+width&&screenX>=x&&screenY>=y&&screenY<=y+height){
    		return true;
    	}
    	return false;
    }

}
