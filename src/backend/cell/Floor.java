package backend.cell;

import java.awt.Point;
import java.io.Serializable;


import backend.Cardinal;
import backend.ContentOperations;
import backend.content.Content;


public class Floor extends Cell implements ContentOperations, Serializable {

	private static final long serialVersionUID = 1L;
	private Content content;

	public Floor() {
		this.content = null;
	}

	public boolean setContent(Content content, Point actual, Cardinal cardinal) {
		if (this.content == null) {
			this.content = content;
			return true;
		} else if (this.content.move(actual, cardinal)) {
			this.content = content;
			return true;
		} else {
			return false;
		}
	}

	public boolean setContent(Content content) {
		if (this.content == null) {
			this.content = content;
			return true;
		}
		return false;
	}

	public Content getContent() {
		return content;
	}

	public boolean removeContent() {
		this.content = null;
		return true;
	}

	public boolean isAccesible() {
		return content == null;
	}
}