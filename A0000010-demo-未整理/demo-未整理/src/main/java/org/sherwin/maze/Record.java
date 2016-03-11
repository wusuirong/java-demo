package org.sherwin.maze;

public class Record {

	Position position;
	Direction direction;
	
	public Record(Position position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
}
