package gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture background,buttons;
	public static TextureRegion bg,lifeButtonUp,lifeButtonDown,magicButtonUp,magicButtonDown;
	
	public static void load(){
		background = new Texture(Gdx.files.internal("data/bg.png"));
        background.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
       
        buttons = new Texture(Gdx.files.internal("data/buttons.png"));
        
        lifeButtonUp = new TextureRegion(buttons, 2, 2, 69, 71);
        lifeButtonDown = new TextureRegion(buttons, 73, 2, 69, 71);
        
        magicButtonUp = new TextureRegion(buttons, 144, 2, 69, 71);
        magicButtonDown = new TextureRegion(buttons, 215, 2, 69, 71);
              
        bg = new TextureRegion(background, 0, 0, 800, 1327);
	}
	
    public static void dispose() {
        background.dispose();
        buttons.dispose();
    }
}
