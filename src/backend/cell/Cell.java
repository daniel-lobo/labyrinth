package backend.cell;

import java.awt.Point;
import java.io.Serializable;


import backend.Cardinal;
import backend.content.Content;

import exceptions.PositionOutOfBoundsException;

public abstract class Cell implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract boolean setContent(Content content, Point boxActualPlace,Cardinal cardinal) throws PositionOutOfBoundsException;
	
	public abstract boolean setContent(Content content);
	
	public abstract boolean isAccesible();
	
	public abstract Content getContent();
}