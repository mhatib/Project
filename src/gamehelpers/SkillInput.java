package gamehelpers;

import gameobjects.SimpleButton;
import gameworld.SkillWorld;
import screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;

public class SkillInput implements InputProcessor{
	SkillWorld myWorld;
	SimpleButton begin;
	private Game game;;
	
    public SkillInput(SkillWorld world, Game game) {
        myWorld = world;
        begin = myWorld.getBeginButton();
        this.game = game;
    }
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (begin.isTouchDown(screenX, screenY)){
			game.setScreen(new GameScreen(game));
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
