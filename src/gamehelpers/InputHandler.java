package gamehelpers;



import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import gameobjects.Diamond;
import gameobjects.Icon;
import gameobjects.SimpleButton;


import gameobjects.*;
import gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	
	GameWorld myWorld;
	private ArrayList<SimpleButton> powerUpButtons;
	boolean withBreak=false;
	boolean withAntidote = false;

    public InputHandler(GameWorld world) {
        myWorld = world;
        powerUpButtons = myWorld.getPowerUpButtons();
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
		if(myWorld.isRunning()){
			int row = (int) ((screenY-(myWorld.getscreenHeight()-myWorld.SIZE*myWorld.getHeight())/2)/myWorld.SIZE);
			int col = (int) ((screenX-(myWorld.getScreenWidth()-myWorld.SIZE*myWorld.getWidth())/2)/myWorld.SIZE);
			if(withBreak&&row>-1&&col>-1&&row<myWorld.getHeight()&&col<myWorld.getWidth()&&myWorld.getIcon(row, col).getSpecial()==2){
				myWorld.getIcon(row, col).setSpecial(0);
				withBreak = false;
			}else if(withBreak){
		
			}else if(withAntidote&&row>-1&&col>-1&&row<myWorld.getHeight()&&col<myWorld.getWidth()&&myWorld.getIcon(row, col).getSpecial()==3){
				myWorld.getIcon(row, col).setSpecial(0);
				withAntidote = false;
			}else if(withAntidote){
		
			}else if(row>-1&&col>-1&&row<myWorld.getHeight()&&col<myWorld.getWidth()&&myWorld.getLife()>0&&myWorld.getIcon(row, col).getSpecial()!=2){
				if(myWorld.getCurrentDiamond()==null){
					myWorld.setCurrentDiamond(row, col, myWorld.getIcon(row, col));
				}else if(myWorld.getNextDiamond()==null&&isAdjacent(myWorld.getCurrentDiamond(),row,col)){
						myWorld.setNextDiamond(row, col, myWorld.getIcon(row, col));
						if(myWorld.getCurrentDiamond().getIcon() != myWorld.getNextDiamond().getIcon()){
							myWorld.getTimer().stop();
							swap(myWorld.getCurrentDiamond(), myWorld.getNextDiamond());
							myWorld.setLife(myWorld.getLife()-1);
							for(int i=0;i<myWorld.getHeight();i++){
								for(int j=0;j<myWorld.getWidth();j++){
									if(myWorld.getIcon(i, j).getSpecial()==1){
										if(myWorld.getIcon(i,j).getSpecialState()>0)
											myWorld.getIcon(i,j).reduceSpecialStateByOne();
										else
											myWorld.getIcon(i, j).setSpecial(0);
									} else if(myWorld.getIcon(i, j).getSpecial()==3){
										myWorld.setLife(myWorld.getLife()-1);
									}
								}
							}
						}
				 		myWorld.setCurrentDiamondToNull();
						myWorld.setNextDiamondToNull();
					}else{
						myWorld.setCurrentDiamond(row, col, myWorld.getIcon(row, col));
						myWorld.setNextDiamondToNull();
					}
				}
			if(powerUpButtons.get(0).isTouchDown(screenX, screenY)){

			} else if (powerUpButtons.get(1).isTouchDown(screenX, screenY)){
				
			} else if (powerUpButtons.get(2).isTouchDown(screenX, screenY)){
				
			} else if (powerUpButtons.get(3).isTouchDown(screenX, screenY)){
				
			} else if (powerUpButtons.get(4).isTouchDown(screenX, screenY)){
				
			} else if (powerUpButtons.get(5).isTouchDown(screenX, screenY)){
				
			} 
		
		} else {
			if(screenY<myWorld.getscreenHeight()/4){
				myWorld.restart();}
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(powerUpButtons.get(0).isTouchUp(screenX, screenY)){
			if(myWorld.getMagic()>2){
				myWorld.setMagic(myWorld.getMagic()-3);
				myWorld.setLife(myWorld.getLife()+1);
			}
		} else if (powerUpButtons.get(1).isTouchUp(screenX, screenY)){
			myWorld.getGenerator().setSomethingSpecial(myWorld.getGrid(), 2, 2);
		} else if (powerUpButtons.get(2).isTouchUp(screenX, screenY)){
			boolean hasFrozenCell = false;
			for(int i=0;i<myWorld.getHeight();i++){
				for(int j=0;j<myWorld.getWidth();j++){
					if(myWorld.getIcon(i, j).getSpecial()==2){
						hasFrozenCell = true;
						break;
					}					
				}
			}
			if(hasFrozenCell)
				withBreak = true;
		} else if (powerUpButtons.get(3).isTouchUp(screenX, screenY)){
			myWorld.getGenerator().setSomethingSpecial(myWorld.getGrid(), 1, 3);
		} else if (powerUpButtons.get(4).isTouchUp(screenX, screenY)){
			boolean hasPoisonCell = false;
			for(int i=0;i<myWorld.getHeight();i++){
				for(int j=0;j<myWorld.getWidth();j++){
					if(myWorld.getIcon(i, j).getSpecial()==3){
						hasPoisonCell = true;
						break;
					}					
				}
			}
			if(hasPoisonCell)
				withAntidote = true;
		} else if (powerUpButtons.get(5).isTouchUp(screenX, screenY)){
			int count = 0;
			for(int i=0;i<myWorld.getHeight();i++){
				for(int j=0;j<myWorld.getWidth();j++){
					if(myWorld.getIcon(i, j).getSpecial()==1){
						count++;
						myWorld.getIcon(i, j).setSpecial(0);
					}					
				}
			}
			myWorld.setMagic(myWorld.getMagic()+count);
		}
		
		return true;
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
	
	private boolean isAdjacent(Diamond a, int row, int col){
		if(a.row()==row||Math.abs(a.row()-row)==1){
			if(a.col()==col||Math.abs(a.col()-col)==1)
				return true;
		}
		return false;
	}
	
	private void swap(Diamond current, Diamond next){
		Icon tmpIcon = myWorld.getIcon(current.row(),current.col());
		myWorld.setIcon(current.row(),current.col(),myWorld.getIcon(next.row(), next.col()));
		myWorld.setIcon(next.row(), next.col(), tmpIcon);
	}
}
