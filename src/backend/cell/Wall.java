package backend.cell;

import java.awt.Point;
import java.io.Serializable;


import backend.Cardinal;
import backend.content.Content;

import exceptions.PositionOutOfBoundsException;

public class Wall extends Cell implements Serializable {

	private static final long serialVersionUID = 1L;

	public Wall() {
	}

	public boolean setContent(Content content) {
		return false;
	}

	public boolean isAccesible() {
		return false;
	}

	public boolean setContent(Content content, Point boxActualPlace,
			Cardinal cardinal) throws PositionOutOfBoundsException {
		return false;
	}

	public Content getContent() {
		return null;
	}
}