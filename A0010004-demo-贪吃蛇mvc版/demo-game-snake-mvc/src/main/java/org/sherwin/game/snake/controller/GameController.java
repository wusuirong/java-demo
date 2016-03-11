package org.sherwin.game.snake.controller;


public interface GameController {

	public void keyUp();
	
	public void keyDown();

	public void keyLeft();

	public void keyRight();
	
	public void startGame();
	
	public void pauseGame();
	
	public void endGame();
	
	public void resetGame();
	
}
