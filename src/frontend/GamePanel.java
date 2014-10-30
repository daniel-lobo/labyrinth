package frontend;

import gui.BoardPanel;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Game;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int CELL_SIZE = 30;

	private final Game game;
	private final BoardPanel bpanel;
	private final JFrame frame;

	private Draw paint;

	public GamePanel(Game g, JFrame frame) {

		this.frame = frame;

		try {
			paint = new Draw();
		} catch (IOException e) {
			new ErrorWindow("Error al cargar imagenes", frame);
		}

		setLayout(null);
		setSize ((g.getWidth()) * CELL_SIZE,(g.getHeight()) * CELL_SIZE);

		game = g;
		bpanel = new BoardPanel(game.getHeight(), game.getWidth(), CELL_SIZE);
		bpanel.setBackground(Color.WHITE);
		add(bpanel);
	}

	public void drawBoard() {
		for (int i = 0; i < game.getHeight(); i++) {
			for (int j = 0; j < game.getWidth(); j++) {
				bpanel.setImage(i, j, paint.drawFloor());
				if (game.getCell(new Point(i, j)).getContent() != null) {
					bpanel.clearImage(i, j);
						bpanel.appendImage(i, j, paint.drawCell(game, i, j));
					}
				bpanel.appendImage(i, j, paint.drawCell(game, i, j));
			}
		}
		bpanel.repaint();

	}
}
