package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import gamehelpers.Generator;
import gamehelpers.InputHandler;
import gameworld.GameRenderer;
import gameworld.GameWorld;

public class GameScreen implements Screen{
	
    private GameWorld world;
    private GameRenderer renderer;
    
    public GameScreen() {
    	float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
/*        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);*/
        world = new GameWorld(7,5, new Generator(6),screenWidth,screenHeight);
        renderer = new GameRenderer(world,screenWidth,screenHeight);
        
        Gdx.input.setInputProcessor(new InputHandler(world));
    }


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		 world.update();
	     renderer .render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
