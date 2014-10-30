package backend;

import java.io.Serializable;

public enum Cardinal implements Serializable {

	NORTH(-1, 0), SOUTH(1, 0), WEST(0, -1), EAST(0, 1);

	private int dirX;
	private int dirY;

	private Cardinal(int dirX, int dirY) {
		this.dirX = dirX;
		this.dirY = dirY;
	}
	
	public int getDirX() {
		return dirX;
	}

	public int getDirY() {
		return dirY;
	}
}