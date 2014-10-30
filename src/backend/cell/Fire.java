package backend.cell;

import java.awt.Point;
import java.io.Serializable;


import backend.Cardinal;
import backend.content.Content;


import exceptions.PositionOutOfBoundsException;

public class Fire extends Floor implements Serializable {

	private static final long serialVersionUID = 1L;

	public Fire() {
	}

	public boolean isAccesible() {
		return true;
	}

	public boolean setContent(Content content, Point boxActualPlace,Cardinal cardinal) throws PositionOutOfBoundsException {
		Boolean ableToSetContent = super.setContent(content, boxActualPlace,cardinal);
		if (ableToSetContent) {
			content.react(boxActualPlace);
		}
		return ableToSetContent;
	}
}