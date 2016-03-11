package org.sherwin.maze;

import java.util.Random;

public class Maze {
	
	Place[][] places;
	
	public Maze(int width, int height) {
		places = new Place[width][height];
		
		for(int i=0; i<width; i++) {
			places[i][0] = new Wall(new Position(i,0));
			places[0][i] = new Wall(new Position(0,i));
			places[i][height-1] = new Wall(new Position(i,height-1));
			places[width-1][i] = new Wall(new Position(width-1,i));
		}
		
		int i = new Random(width).nextInt();
		int j = new Random(height).nextInt();
		
		places[i][j] = new Exit(new Position(i,j));
	}

}
