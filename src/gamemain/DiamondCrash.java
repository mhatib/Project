package gamemain;

import gamehelpers.AssetLoader;
import screens.SkillScreen;

import com.badlogic.gdx.Game;

public class DiamondCrash extends Game{
	
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SkillScreen(this));
	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
	
}
