package org.sherwin.game.snake.model;

import java.util.ArrayList;
import java.util.List;

public class Ground {

	private int width;
	private int height;
	
	private Snake snake;
	private List<Rock> rocks;
	private List<Food> foods;
	
	public Ground(int width, int height, Snake snake, List<Rock> rocks, List<Food> foods) {
		this.width = width;
		this.height = height;
		this.snake = snake;
		this.rocks = rocks;
		this.foods = foods;		
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the snake
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * @param snake the snake to set
	 */
	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	/**
	 * @return the rocks
	 */
	public List<Rock> getRocks() {
		return rocks;
	}

	/**
	 * @param rocks the rocks to set
	 */
	public void setRocks(List<Rock> rocks) {
		this.rocks = rocks;
	}

	/**
	 * @return the foods
	 */
	public List<Food> getFoods() {
		return foods;
	}

	/**
	 * @param foods the foods to set
	 */
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
}
