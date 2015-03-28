package gameworld;

import gamehelpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;



public class SkillRenderer {
	private SkillWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private SpriteBatch batcher;
    private static float screenWidth;
    private static float screenHeight;
    private float midPointY;
    private float gameHeight;


    public SkillRenderer(SkillWorld world, float gameHeight, float midPointY) {
        myWorld = world;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();  
        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth/2, screenHeight/2);
        //batch.setProjectionMatrix(cam.combined);

        screenWidth = gameHeight;
        screenHeight = midPointY;
        System.out.println(screenWidth);
        System.out.println(screenHeight);
    }

    public void render() {

    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.disableBlending();
        batch.draw(AssetLoader.bg, 0, 0, screenWidth, screenHeight);
        batch.end();

/*        powerUpButtons.get(0).draw(batch);
        powerUpButtons.get(5).draw(batch);*/
        
        //SimpleButton a = new Simplebutton()
        //new SimpleButton((screenWidth)/2, (screenHeight)/2, AssetLoader.lifeButtonUp,AssetLoader.lifeButtonDown, screenHeight)
    }
}
