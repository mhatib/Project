package gameworld;



import java.util.ArrayList;

import gameobjects.Diamond;
import gameobjects.Icon;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import gamehelpers.AssetLoader;
import gameobjects.SimpleButton;
import gameworld.AnimatedDiamond;
import gameworld.GameWorld;


public class GameRenderer {
	
	private Color[] colors = {
		      Color.RED,
		      Color.GREEN,
		      Color.CYAN,
		      Color.YELLOW,
		      Color.ORANGE, 
		      //Color.LIGHT_GRAY,
		      Color.PINK};
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;
    private static float screenWidth;
    private static float screenHeight;
    private int flashingState;
    private ArrayList<SimpleButton> powerUpButtons;


    public GameRenderer(GameWorld world,float w,float h) {
        myWorld = world;
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();  
        cam = new OrthographicCamera();
        cam.setToOrtho(true, screenWidth/2, screenHeight/2);
        //batch.setProjectionMatrix(cam.combined);
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(1f, 1f);
        screenWidth = w;
        screenHeight = h;
        flashingState = 0;
        powerUpButtons= myWorld.getPowerUpButtons();
        System.out.println(screenWidth);
        System.out.println(screenHeight);
    }
    

    public void render() {

    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.disableBlending();
        batch.draw(AssetLoader.bg, 0, 0, screenWidth, screenHeight);
        batch.end();
        
        for(SimpleButton button: powerUpButtons)
        	button.draw(batch);
/*        powerUpButtons.get(0).draw(batch);
        powerUpButtons.get(5).draw(batch);*/

       batch.begin();
             
        //font.draw(batch, "SCORE:"+myWorld.getScore(), screenWidth/2-150-(30*((myWorld.getScore()+"").length())), screenHeight*19/20);
        font.draw(batch, "SCORE:"+myWorld.getScore(), (screenWidth-myWorld.SIZE*myWorld.getWidth())/2, screenHeight*19/20);
        font.draw(batch, "LIFE:"+myWorld.getLife(), (screenWidth-myWorld.SIZE*myWorld.getWidth())/2, screenHeight*9/10);
        font.draw(batch, "MAGIC:"+myWorld.getMagic(), (screenWidth-myWorld.SIZE*myWorld.getWidth())/2+300, screenHeight*9/10);
        batch.end();
        
        flashingState = (flashingState+1)%20;
        
        drawTimer(shapeRenderer, 1);
        
        for (int row = 0; row < myWorld.getHeight(); ++row){
        	for (int col = 0; col < myWorld.getWidth(); ++col){
        		Icon t = myWorld.getIcon(row, col);
        		if (t != null){
        			drawOneCell(shapeRenderer, row, col, t);
        			if(t.getSpecial()==1&&(t.getSpecialState()>1||(t.getSpecialState()==1&&flashingState<10)))
        				drawOneSpecialCell(shapeRenderer, row, col,Color.WHITE); 
        			else if(t.getSpecial()==2)
        				drawOneSpecialCell(shapeRenderer, row, col,Color.BLACK);
        			else if(t.getSpecial()==3)
        				drawOneSpecialCell(shapeRenderer, row, col, Color.GRAY);
        				
        		}

          }
        }
        
        if(myWorld.getCurrentDiamond()!=null){
        	highlightOneCell(shapeRenderer, myWorld.getCurrentDiamond().row(),myWorld.getCurrentDiamond().col());
        }
        if(myWorld.getNextDiamond()!=null){
        	highlightOneCell(shapeRenderer, myWorld.getNextDiamond().row(),myWorld.getNextDiamond().col());
        }
        
        if (myWorld.getFlashingState() > 0){
        	for (Diamond d : myWorld.getDiamondsToCollapse()){
        		drawOneCell(shapeRenderer, d.row(), d.col(), d.getIcon());
        		if (myWorld.getFlashingState() % 10 < 5){
        			highlightOneCell(shapeRenderer, d.row(), d.col());
        		}
        	}    
        }
   
        if (myWorld.getCollapsing()){
        	for (AnimatedDiamond d : myWorld.getMovingDiamonds()){
        		int col = d.col();
        		int row = d.row();
        		drawOneCell(shapeRenderer, row, col,Color.BLACK);
        	} 
        	for (AnimatedDiamond d : myWorld.getMovingDiamonds()){
        		int col = d.col();
        		float pixel = d.currentPixel;
        		paintOneCellByPixel(shapeRenderer, pixel, col, d.getIcon());
        	}
        }
        
        if (myWorld.getFilling()){
        	for (AnimatedDiamond d : myWorld.getFillingDiamonds()){
        		int col = d.col();
        		int row = d.row();
        		drawOneCell(shapeRenderer, row, col, Color.BLACK);
        	}
        	for (AnimatedDiamond d : myWorld.getFillingDiamonds()){
        		int col = d.col();
        		float pixel = d.currentPixel;
        		paintOneCellByPixel(shapeRenderer, pixel, col, d.getIcon());
        	}
          
        }
        
        
    }
    
    
    private void drawOneCell(ShapeRenderer sr, int row, int col, Icon t){
    	sr.begin(ShapeType.Filled);
    	sr.setColor(getColorForIcon(t));
    	sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col, screenHeight-(screenHeight-myWorld.SIZE*myWorld.getHeight())/2-myWorld.SIZE*(row+1), myWorld.SIZE, myWorld.SIZE);
    	sr.end();
    	sr.begin(ShapeType.Line);
    	sr.setColor(Color.BLACK);
    	sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col, screenHeight-(screenHeight-myWorld.SIZE*myWorld.getHeight())/2-myWorld.SIZE*(row+1), myWorld.SIZE, myWorld.SIZE);
    	sr.end();
    }
    
    private void drawOneCell(ShapeRenderer sr, int row, int col, Color c){
    	sr.begin(ShapeType.Filled);
    	sr.setColor(c);
    	sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col, screenHeight-(screenHeight-myWorld.SIZE*myWorld.getHeight())/2-myWorld.SIZE*(row+1), myWorld.SIZE, myWorld.SIZE);
    	sr.end();
    	sr.begin(ShapeType.Line);
    	sr.setColor(Color.BLACK);
    	for(int i=0;i<3;i++){
    		sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col+i, screenHeight-(screenHeight-myWorld.SIZE*myWorld.getHeight())/2-myWorld.SIZE*(row+1)+i, myWorld.SIZE-i, myWorld.SIZE-i);
    	}
    	sr.end();
    }
    
    private Color getColorForIcon(Icon icon){
    	int index = icon.getType();
    	if (index < 0 || index > colors.length)
    		return Color.BLACK;
    	return colors[index];
    }
    
    private void highlightOneCell(ShapeRenderer sr, int row, int col){
    	sr.begin(ShapeType.Line);
    	sr.setColor(Color.WHITE);
    	for(int i=0;i<3;i++){
    		sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col+i, screenHeight-(screenHeight-myWorld.SIZE*myWorld.getHeight())/2-myWorld.SIZE*(row+1)+i, myWorld.SIZE-i, myWorld.SIZE-i);
    	}
    	sr.end();
    }
    
    private void drawOneSpecialCell(ShapeRenderer sr, int row, int col,Color c){
    	sr.begin(ShapeType.Filled);
    	sr.setColor(c);
    	sr.circle((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*(col)+myWorld.SIZE/2, screenHeight-(screenHeight-myWorld.SIZE*myWorld.getHeight())/2-myWorld.SIZE*(row)-myWorld.SIZE/2, myWorld.SIZE/4);
    	sr.end();
    }
    
    private void drawTimer(ShapeRenderer sr, long timeElapsed){
    	sr.begin(ShapeType.Filled);
    	sr.setColor(Color.WHITE);
    	sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2, screenHeight*4/5, myWorld.SIZE*myWorld.getWidth()-myWorld.SIZE*myWorld.getWidth()*myWorld.getTimer().getTimeElapsed()/myWorld.getWaitingTime(), 30);
    	sr.end();
    }
    
    private void drawButton(ShapeRenderer sr, float x, float y, Color c){
    	sr.begin(ShapeType.Filled);
    	sr.setColor(c);
    	sr.rect(x, y, screenWidth-(screenWidth-myWorld.SIZE*myWorld.getWidth())/2-x, 100);
    	sr.end();
    }
            
    private void paintOneCellByPixel(ShapeRenderer sr, float rowPixel, int col, Icon t){
    	
    	sr.begin(ShapeType.Filled);
    	sr.setColor(getColorForIcon(t));
    	sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col, rowPixel, myWorld.SIZE, myWorld.SIZE);
    	sr.end();
    	sr.begin(ShapeType.Line);
    	sr.setColor(Color.BLACK);
    	sr.rect((screenWidth-myWorld.SIZE*myWorld.getWidth())/2+myWorld.SIZE*col, rowPixel, myWorld.SIZE-1, myWorld.SIZE-1);
    	sr.end();
    }
    
    
}


class AnimatedDiamond extends Diamond{
	
	public float startPixel; 
	public float endPixel;
	public float currentPixel;


	public AnimatedDiamond(Diamond diamond,float screenHeight,int height){
		super(diamond.row(), diamond.col(), diamond.getIcon());
		startPixel = screenHeight-(screenHeight-GameWorld.SIZE*height)/2-GameWorld.SIZE*(diamond.getPreviousRow()+1);
		currentPixel = startPixel;
		endPixel = screenHeight-(screenHeight-GameWorld.SIZE*height)/2-GameWorld.SIZE*(diamond.row()+1);
	}

	public AnimatedDiamond(Diamond diamond, int startRow,float screenHeight,int height){
		super(diamond.row(), diamond.col(), diamond.getIcon());
		this.setPreviousRow(startRow);
		startPixel = screenHeight-(screenHeight-GameWorld.SIZE*height)/2-GameWorld.SIZE*(diamond.getPreviousRow()+1);
		currentPixel = startPixel;
		endPixel = screenHeight-(screenHeight-GameWorld.SIZE*height)/2-GameWorld.SIZE*(diamond.row()+1);
	}

	public boolean done(){
		return currentPixel == endPixel;
	}
  
	public void animate(int pixels){
		currentPixel += pixels;
		if (currentPixel > endPixel){
			currentPixel = endPixel;
		}
	}
	
}

