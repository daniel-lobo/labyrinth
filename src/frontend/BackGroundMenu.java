package frontend;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackGroundMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	public BackGroundMenu() {
		setOpaque(false);
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		Image imagen = Toolkit.getDefaultToolkit().getImage(
				"./resources/labyrinth.jpg");
		g.drawImage(imagen, 0, 0, getSize().width, getSize().height, this);
		super.paint(g);
	}

}