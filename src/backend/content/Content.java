package backend.content;

import java.awt.Point;
import java.io.Serializable;

import backend.Cardinal;
import backend.Game;

public abstract class Content implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Point position;

	public Content(Point point) {
		this.position = point;
	}

	public void setPosition(Cardinal direction) {
		if ((Game.getInstance().validPosition(position))) {
			position.x += direction.getDirX();
			position.y += direction.getDirY();
		}
	}

	public abstract boolean move(Point actualPlace, Cardinal cardinal);

	public abstract boolean remove();

	public Point getPosition() {
		return position;
	}

	public abstract void react(Point ActualPlace);

	public abstract boolean interuptorReact();
}