package org.sherwin.game.snake.controller;

import org.sherwin.game.snake.model.Direction;
import org.sherwin.game.snake.model.ModelManager;
import org.sherwin.game.snake.view.GameWindow;

public class GameControllerImpl {

	private GameWindow gameWindow;
	
	private ModelManager modelManager;

	public void keyUp() {
		modelManager.changeSnakeDirection(Direction.UP);
	}
	
	public void keyDown() {
		modelManager.changeSnakeDirection(Direction.DOWN);
	}

	public void keyLeft() {
		modelManager.changeSnakeDirection(Direction.LEFT);
	}

	public void keyRight() {
		modelManager.changeSnakeDirection(Direction.RIGHT);
	}
	
	public void startGame() {
		modelManager.startGame();
	}
	
	public void pauseGame() {
		modelManager.pauseGame();
	}
	
	public void endGame() {
		modelManager.endGame();
		gameWindow.endGame();
	}
	
	public void resetGame() {
		modelManager.init();
//		gameWindow.resetUI();
	}

	/**
	 * @return the gameWindow
	 */
	public GameWindow getGameWindow() {
		return gameWindow;
	}

	/**
	 * @param gameWindow the gameWindow to set
	 */
	public void setGameWindow(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}

	/**
	 * @return the modelManager
	 */
	public ModelManager getModelManager() {
		return modelManager;
	}

	/**
	 * @param modelManager the modelManager to set
	 */
	public void setModelManager(ModelManager modelManager) {
		this.modelManager = modelManager;
	}
	
}
