package org.sherwin.game.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class ModelManager extends Observable implements Runnable {

	private Snake snake;
	private List<Rock> rocks;
	private List<Food> foods;
	private Ground ground;
	
	private GameSettings gameSettings;
	
	private GameStatus gameStatus;
	
	private Random r = new Random(System.currentTimeMillis());
	
	public ModelManager() {
		gameSettings = new GameSettings(5, 8, 8);
		
		snake = new Snake(0, 0);
		rocks = new ArrayList<Rock>();
		foods = new ArrayList<Food>();

		ground = new Ground(gameSettings.getGroundWidth(), gameSettings.getGroundHeight(), snake, rocks, foods);

		init();
		Thread t = new Thread(this);
		t.start();
	}
	
	public void init() {
		gameSettings.setSpeed(5);
		gameSettings.setGroundHeight(8);
		gameSettings.setGroundWidth(8);
		
		snake.reset(0, 0);
		rocks.clear();
		foods.clear();

		ground = new Ground(gameSettings.getGroundWidth(), gameSettings.getGroundHeight(), snake, rocks, foods);
		
		gameStatus = GameStatus.READY;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public void run() {
		while (true) {
			if (gameStatus.equals(GameStatus.RUNNING)) {
				randomEvent();
				snake.move();
				if (snake.hitRock(rocks)) {
					this.endGame();
				}
				if (snake.hitMyself()) {
					this.endGame();
				}
				if (snake.outbound(ground)) {
					this.endGame();
				}
				Food food = snake.eatFood(foods);
				if (null != food) {
					foods.remove(food);
					snake.growUp(1);
				}
				this.setChanged();
				this.notifyObservers();
			}
			
			try {
				Thread.sleep(gameSettings.getSpeed()*100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void changeSnakeDirection(Direction direction) {
		snake.changeDirection(direction);
	}
	
	private void randomEvent() {
		int i = r.nextInt(3);
		if (0==i && foods.size()<3) {
			int col = r.nextInt(gameSettings.getGroundWidth());
			int row = r.nextInt(gameSettings.getGroundHeight());
			Food food = new Food(col, row);
			foods.add(food);
		}
		i = r.nextInt(8);
		if (4<i) {
			int col = r.nextInt(gameSettings.getGroundWidth());
			int row = r.nextInt(gameSettings.getGroundHeight());
			boolean exists = false;
			for (Rock rock : rocks) {
				if (col == rock.getX() && row == rock.getY()) {
					exists = true;
					break;
				}
			}
			for (Food food : foods) {
				if (col == food.getX() && row == food.getY()) {
					exists = true;
					break;
				}
			}
			List<Snake.Node> nodes = snake.getNodes();
			for (Snake.Node node : nodes) {
				if (col == node.getX() && row == node.getY()) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				Rock rock = new Rock(col, row);
				rocks.add(rock);
			}
		}
	}
	
	public void startGame() {
		if (gameStatus.equals(GameStatus.READY)
				|| gameStatus.equals(GameStatus.PAUSE)) {
			gameStatus = GameStatus.RUNNING;
		}
		
	}
	
	public void pauseGame() {
		if (gameStatus.equals(GameStatus.RUNNING)) {
			gameStatus = GameStatus.PAUSE;
		}
	}
	
	public void endGame() {
		gameStatus = GameStatus.END;
	}
	
	public void resetGame() {
		init();
	}
	
	public int getGameSpeed() {
		return gameSettings.getSpeed();	
	}
	
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	
	public Ground getGround() {
		return ground;
	}
}
