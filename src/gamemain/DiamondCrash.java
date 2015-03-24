package gamemain;

import com.badlogic.gdx.Game;
import gamehelpers.AssetLoader;

import screens.GameScreen;

public class DiamondCrash extends Game{
	
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
	
}
