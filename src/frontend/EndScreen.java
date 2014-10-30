package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndScreen extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JFrame frame;

	public EndScreen(final JFrame frame, String estado) {
		this.frame = frame;
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(250, 100);
		setTitle("Game Over");
		centerScreen();
		JLabel label = new JLabel("" + estado + "!", JLabel.CENTER);

		add(label);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
				setVisible(false);
				new MainMenu();
			}
		});

	}

	public void centerScreen() {
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
	}

}