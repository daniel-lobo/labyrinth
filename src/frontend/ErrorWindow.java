package frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	public ErrorWindow(String str, final JFrame frame) {

		frame.setEnabled(false);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setResizable(false);

		setSize(250, 100);
		centerScreen();
		setTitle("ERROR");

		JLabel label = new JLabel(str, JLabel.CENTER);

		add(label);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				frame.setEnabled(true);
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
