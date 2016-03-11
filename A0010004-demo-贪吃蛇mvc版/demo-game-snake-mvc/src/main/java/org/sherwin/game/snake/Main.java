package org.sherwin.game.snake;

import org.sherwin.game.snake.controller.GameControllerImpl;
import org.sherwin.game.snake.model.ModelManager;
import org.sherwin.game.snake.view.GameWindow;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ModelManager modelManager = new ModelManager();
		GameControllerImpl gameController = new GameControllerImpl();
		GameWindow gameWindow = new GameWindow(gameController, modelManager);
		modelManager.addObserver(gameWindow);
		gameController.setGameWindow(gameWindow);
		gameController.setModelManager(modelManager);
	}

}
