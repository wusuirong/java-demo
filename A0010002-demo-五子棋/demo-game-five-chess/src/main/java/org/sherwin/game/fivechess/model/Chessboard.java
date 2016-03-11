package org.sherwin.game.fivechess.model;

public class Chessboard {
	
	private int xLines;
	private int yLines;

	private int[][] points;
	
	public Chessboard() {
		points = new int[xLines][yLines];
	}
	
	public int getPointStatus(int x, int y) {
		if (0 <= x && x < xLines && 0 <= y && y < yLines) {
			return points[x][y];
		} else {
			return -1;
		}
	}
	
	public void setPointStatus(int x, int y, int status) {
		if (0 <= x && x < xLines && 0 <= y && y < yLines) {
			points[x][y] = status;
		}
	}
}
