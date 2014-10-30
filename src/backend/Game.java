package backend;

import java.awt.Point;
import java.io.Serializable;

import backend.cell.Cell;
import backend.cell.Interruptor;
import backend.content.Content;
import backend.content.Player;



import exceptions.PositionOutOfBoundsException;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cell[][] board = null;
	private int height = 0;
	private int width = 0;
	private State state = State.PLAYING;
	private Player player = null;
	private Interruptor inter = null;
	private static Game game = null;
	
	private Game(int height, int width) {
		this.height = height;
		this.width = width;
		board = new Cell[height][width];
	}
	
	public static Game getInstance(){
		if (game == null) {
			throw new GameNotCreatedException();
		}
		return game;
	}
	
	public static Game createInstance(int width, int height){
		if (game == null) {
			game = new Game(width, height);
		}
		return game;
	}

	public Cell[][] getBoard() {
		return board;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player element) {
		this.player = element;
	}

	public boolean move(Cardinal cardinal) throws PositionOutOfBoundsException {

		Boolean AbletoMove;
		Point toPoint = SetPositionCardinal(player.getPosition(), cardinal);

		if (validPosition(toPoint) && board[toPoint.x][toPoint.y].setContent(player, toPoint,cardinal)) {
			((ContentOperations) board[player.getPosition().x][player.getPosition().y]).removeContent();
			player.setPosition(cardinal);

			AbletoMove = true;
		} else
			AbletoMove = false;

		return AbletoMove;
	}

	public boolean putCell(Cell cell, Point position) {
		board[position.x][position.y] = cell;
		return true;
	}

	public boolean putContent(Content cont, Point position) {
		if (board[position.x][position.y] instanceof ContentOperations
				&& ((ContentOperations) board[position.x][position.y])
						.getContent() == null) {
			board[position.x][position.y].setContent(cont);
			return true;
		}
		return false;
	}

	public Cell getCell(Point position) {
		return board[position.x][position.y];
	}

	public boolean validPosition(Point p) {
		return (p.x >= 0 && p.x < height && p.y >= 0 && p.y < width);
	}

	public boolean isInterruptorActive() {
		Content aux = inter.getContent();
		if (aux != null) {
			return (inter.getContent().interuptorReact());
		} else {
			return false;
		}
	}

	public void setInterruptor(Interruptor inter) {
		this.inter = inter;
	}

	public Point SetPositionCardinal(Point p, Cardinal c) {
		return (new Point((int) p.getX() + c.getDirX(),
				((int) p.getY() + c.getDirY())));
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}