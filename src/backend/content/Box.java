package backend.content;

import java.awt.Point;
import java.io.Serializable;

import backend.Cardinal;
import backend.ContentOperations;
import backend.Game;
import backend.cell.FireBox;



import exceptions.PositionOutOfBoundsException;

public class Box extends Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Box(Point point) {
		super(point);
	}

	public boolean move(Point boxActualPlace, Cardinal cardinal) throws PositionOutOfBoundsException {
		
		Point boxNextPlace = Game.getInstance().SetPositionCardinal(boxActualPlace, cardinal);
		
		if (Game.getInstance().validPosition(boxNextPlace)) {
			if (Game.getInstance().getCell(boxNextPlace) instanceof ContentOperations && ((ContentOperations) Game.getInstance().getCell(boxNextPlace)).getContent() == null) {
				if (Game.getInstance().getCell(boxNextPlace).setContent(this, boxNextPlace, cardinal)) {
					if (((ContentOperations) Game.getInstance().getCell(boxActualPlace)).getContent() != null) {
						((ContentOperations) Game.getInstance().getCell(boxActualPlace)).getContent().setPosition(cardinal);
						((ContentOperations) Game.getInstance().getCell(boxActualPlace)).removeContent();
						return true;
					}
					return true;
				}

			} else if (((ContentOperations)(Game.getInstance().getCell(boxActualPlace))).getContent() == null) {
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
		Game.getInstance().putCell(new FireBox(), boxActualPlace);
	}

	public boolean interuptorReact() {
		return true;
	}

}