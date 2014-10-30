package backend.cell;

import java.awt.Point;
import java.io.Serializable;

import backend.Cardinal;
import backend.content.Content;

public class Interruptor extends Floor implements Serializable {

	private static final long serialVersionUID = 1L;

	public Interruptor() {
		super();
	}

	public boolean setContent(Content content, Point actual, Cardinal cardinal) {
		return super.setContent(content, actual, cardinal);
	}

}