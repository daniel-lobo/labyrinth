package backend.content;

import java.awt.Point;
import java.io.Serializable;

import backend.Cardinal;
import backend.ContentOperations;
import backend.cell.FireBox;



import exceptions.PositionOutOfBoundsException;

public class Box extends Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Box(Point point) {
		super(point);
	}

	public boolean move(Point boxActualPlace, Cardinal cardinal) throws PositionOutOfBoundsException {
		
		Point boxNextPlace = this.getGame().SetPositionCardinal(boxActualPlace, cardinal);
		
		if (this.getGame().validPosition(boxNextPlace)) {
			if (this.getGame().getCell(boxNextPlace) instanceof ContentOperations && ((ContentOperations) this.getGame().getCell(boxNextPlace)).getContent() == null) {
				if (this.getGame().getCell(boxNextPlace).setContent(this, boxNextPlace, cardinal)) {
					if (((ContentOperations) this.getGame().getCell(boxActualPlace)).getContent() != null) {
						((ContentOperations) this.getGame().getCell(boxActualPlace)).getContent().setPosition(cardinal);
						((ContentOperations) this.getGame().getCell(boxActualPlace)).removeContent();
						return true;
					}
					return true;
				}

			} else if (((ContentOperations)(this.getGame().getCell(boxActualPlace))).getContent() == null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove() {
		return false;
	}

	public void react(Point boxActualPlace){
		getGame().putCell(new FireBox(), boxActualPlace);
	}

	public boolean interuptorReact() {
		return true;
	}

}