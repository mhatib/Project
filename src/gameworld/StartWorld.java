package gameworld;

import gamehelpers.AssetLoader;
import gamehelpers.Generator;
import gameobjects.SimpleButton;

public class StartWorld {

    private SimpleButton begin = new SimpleButton(0, 0, 0, 0, null, null, 0);
	public static final int SIZE = 150;
	private int width;
	private int height;
    private static float screenHeight;
    private static float screenWidth;
	
	public StartWorld(int i, int j, Generator generator, float w,	float h) {
		// TODO Auto-generated constructor stub
		this.width = j;
		this.height = i;
	    screenHeight = h;
	    screenWidth = w;

	    
	    begin = new SimpleButton((screenWidth/2-SIZE/2), (screenHeight-SIZE)-SIZE*5, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public SimpleButton getBeginButton(){
		return begin;
	}
}
