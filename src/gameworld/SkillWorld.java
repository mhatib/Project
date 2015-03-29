package gameworld;

import java.util.ArrayList;

import gamehelpers.AssetLoader;
import gamehelpers.Generator;
import gameobjects.SimpleButton;

public class SkillWorld {
    private ArrayList<SimpleButton> powerUpButtons = new ArrayList<SimpleButton>();
    private ArrayList<SimpleButton> defensivePowers = new ArrayList<SimpleButton>();
    private SimpleButton begin = new SimpleButton(0, 0, 0, 0, null, null, 0);
	public static final int SIZE = 150;
	private int width;
	private int height;
    private static float screenHeight;
    private static float screenWidth;
	
	public SkillWorld(int i, int j, Generator generator, float w,	float h) {
		// TODO Auto-generated constructor stub
		this.width = j;
		this.height = i;
	    screenHeight = h;
	    screenWidth = w;
	    System.out.println(screenWidth);
	    System.out.println(screenHeight);
	    powerUpButtons.add(new SimpleButton((screenWidth-SIZE*width)/2, (screenHeight-SIZE)-SIZE, SIZE, SIZE,AssetLoader.lifeButtonUp,AssetLoader.lifeButtonDown, screenHeight));
	    powerUpButtons.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*2, (screenHeight-SIZE)-SIZE, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    powerUpButtons.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*2, (screenHeight-SIZE)-SIZE*2, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    powerUpButtons.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*4, (screenHeight-SIZE)-SIZE, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    powerUpButtons.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*4, (screenHeight-SIZE)-SIZE*2, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    powerUpButtons.add(new SimpleButton((screenWidth-SIZE*width)/2, (screenHeight-SIZE)-SIZE*2, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    
	    defensivePowers.add(new SimpleButton((screenWidth-SIZE*width)/2, (screenHeight-SIZE)-SIZE*4, SIZE, SIZE,AssetLoader.lifeButtonUp,AssetLoader.lifeButtonDown, screenHeight));
	    defensivePowers.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*2, (screenHeight-SIZE)-SIZE*4, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    defensivePowers.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*2, (screenHeight-SIZE)-SIZE*5, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    defensivePowers.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*4, (screenHeight-SIZE)-SIZE*4, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    defensivePowers.add(new SimpleButton((screenWidth-SIZE*width)/2+SIZE*4, (screenHeight-SIZE)-SIZE*5, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    defensivePowers.add(new SimpleButton((screenWidth-SIZE*width)/2, (screenHeight-SIZE)-SIZE*5, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight));
	    
	    begin = new SimpleButton((screenWidth/2-SIZE/2), (screenHeight-SIZE)-SIZE*8, SIZE, SIZE,AssetLoader.magicButtonUp,AssetLoader.magicButtonDown, screenHeight);
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<SimpleButton> getPowerUpButtons(){
		return powerUpButtons;
	}
	
	public ArrayList<SimpleButton> getDefensivePowers(){
		return defensivePowers;
	}
	
	public SimpleButton getBeginButton(){
		return begin;
	}
	
	

}
