package org.sherwin.maze;

public enum Direction {
	EAST(0),SOUTH(1),WEST(2),NORTH(3);
	
	int direction;
	
	private Direction(int direction) {
		this.direction = direction;
	}
	
	public Direction getNextDirection(Direction direction) {
		switch(direction) {
		case EAST:
			return SOUTH;
		case SOUTH:
			return WEST;
		case WEST:
			return NORTH;
		case NORTH:
			return EAST;
		default:
			return null;
		}
	}
}
