package backend.cell;

import java.awt.Point;
import java.io.Serializable;


import backend.Cardinal;
import backend.State;
import backend.content.Content;
import backend.content.Player;


import exceptions.PositionOutOfBoundsException;

public class Target extends Floor implements Serializable {

	private static final long serialVersionUID = 1L;

	public Target() {
		super();
	}

	public boolean setContent(Content content, Point actual, Cardinal cardinal)
			throws PositionOutOfBoundsException {
		Boolean ableToSetContent = super.setContent(content, actual, cardinal);
		if (ableToSetContent && ((this.getContent()) instanceof Player) && interruptorActive()) {
			this.getContent().getGame().setState(State.WIN);
		}
		return ableToSetContent;
	}

	public boolean interruptorActive() {
		return (this.getContent().getGame().isInterruptorActive());
	}

}