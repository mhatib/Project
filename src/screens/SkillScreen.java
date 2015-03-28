package screens;

import gamehelpers.Generator;
import gamehelpers.SkillInput;
import gameworld.SkillRenderer;
import gameworld.SkillWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class SkillScreen implements Screen{
	private SkillWorld world;
    private SkillRenderer renderer;
    private Game game;
    public SkillScreen(Game game) {
    	this.game = game;
    	float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
/*        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);*/
        world = new SkillWorld(7,5, new Generator(6),screenWidth,screenHeight);
        renderer = new SkillRenderer(world,screenWidth,screenHeight);
        
        Gdx.input.setInputProcessor(new SkillInput(world));
    }


	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()){
            game.setScreen(new GameScreen(game));
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
