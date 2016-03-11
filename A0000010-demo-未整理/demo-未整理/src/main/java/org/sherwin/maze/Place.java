package org.sherwin.maze;

public abstract class Place {
	
	Position position;
	
	public Place(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
