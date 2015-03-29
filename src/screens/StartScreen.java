package screens;

import gamehelpers.Generator;
import gameworld.SkillRenderer;
import gameworld.SkillWorld;
import gameworld.StartRenderer;
import gameworld.StartWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class StartScreen implements Screen{
	private StartWorld world;
    private StartRenderer renderer;
    private Game game;
    public StartScreen(Game game) {
    	this.game = game;
    	float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        world = new StartWorld(7,5, new Generator(6),screenWidth,screenHeight);
        renderer = new StartRenderer(world,screenWidth,screenHeight);        
        //Gdx.input.setInputProcessor(new SkillInput(world));
    }


	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()){
            game.setScreen(new SkillScreen(game));
		}
		 world.update();
	     renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//play.setBounds(0,568,720,164);
		
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
