package gameworld;

import gamehelpers.AssetLoader;
import gameobjects.SimpleButton;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartRenderer {
	private StartWorld myWorld;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private static float screenWidth;
    private static float screenHeight;
    private ArrayList<SimpleButton> powerUpButtons;
    private ArrayList<SimpleButton> defensivePowers;
    private SimpleButton begin;


    public StartRenderer(StartWorld world, float w, float h) {
        myWorld = world;
        batch = new SpriteBatch();  
        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth/2, screenHeight/2);
        screenWidth = w;
        screenHeight = h;
        begin = myWorld.getBeginButton();

    }

    public void render() {
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.disableBlending();
        batch.draw(AssetLoader.bg, 0, 0, screenWidth, screenHeight);
        batch.end();

        
        begin.draw(batch);
        

    }
}
