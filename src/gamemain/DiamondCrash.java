package gamemain;

import gamehelpers.AssetLoader;
import screens.StartScreen;

import com.badlogic.gdx.Game;

public class DiamondCrash extends Game{
	
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new StartScreen(this));
	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
	
}
