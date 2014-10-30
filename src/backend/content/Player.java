package backend.content;

import java.awt.Point;
import java.io.Serializable;

import backend.Cardinal;
import backend.State;

public class Player extends Content implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Player(Point point) {
		super(point);
	}

	public boolean remove() {
		this.getGame().setState(State.LOSE);
		return false;
	}

	@Override
	public boolean move(Point going, Cardinal cardinal) {
		return false;
	}

	public void react(Point actualPlace) {
		remove();
	}

	@Override
	public boolean interuptorReact() {
		return false;
	}
}