package backend.content;

import java.awt.Point;
import java.io.Serializable;

import backend.Cardinal;
import backend.Game;

public abstract class Content implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Point position;
	private final Game game;

	public Content(Point point) {
		this.position = point;
		this.game = Game.getInstance();
	}

	public void setPosition(Cardinal direction) {
		if ((game.validPosition(position))) {
			position.x += direction.getDirX();
			position.y += direction.getDirY();
		}
	}

	public abstract boolean move(Point actualPlace, Cardinal cardinal);

	public abstract boolean remove();

	public Game getGame() {
		return game;
	}

	public Point getPosition() {
		return position;
	}

	public abstract void react(Point ActualPlace);

	public abstract boolean interuptorReact();
}