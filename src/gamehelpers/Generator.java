package gamehelpers;

import java.util.Random;



import gameobjects.*;

public class Generator{
	private int type;
	private Random random;
	
	public Generator(int num) {
		type = num;
		random = new Random();
	}

	public Icon generate() {
		Icon newIcon = new Icon(random.nextInt(type));
		return newIcon;
	}
	

	public void initialize(Icon[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = generate();
/*				if ((i + j) % 2 == 0) {
					grid[i][j] = new Icon(0);
				} else {
					grid[i][j] = new Icon(1);
				}*/
			}
		}
		setOneSpecial(grid);
	}
	
	public int randomNumber(int num){
		return random.nextInt(num);
	}
	
	public void setOneSpecial(Icon[][] grid){
		int a = randomNumber(grid.length);
		int b = randomNumber(grid[0].length);
		grid[a][b].setSpecial(1);
		
	}
	
	public void setSomethingSpecial(Icon[][] grid,int n, int specialType){
		for(int i=0;i<n;i++){
			int a,b;
			do{
				a = randomNumber(grid.length);
				b = randomNumber(grid[0].length);}while(grid[a][b].getSpecial()!=0);
			grid[a][b].setSpecial(specialType);
		}
	}

}
