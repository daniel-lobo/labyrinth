package frontend;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import backend.Game;
import backend.SaveandLoadGame;


public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainMenu() {
		setTitle("Labyrinth");
		setBounds(1, 1, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		BackGroundMenu back = new BackGroundMenu();

		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);

		JButton newGame = new JButton("New Game");
		newGame.setBounds(20, 150, 140, 25);
		newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton loadGame = new JButton("Load Game");
		loadGame.setBounds(20, 190, 140, 25);
		loadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JButton exitGame = new JButton("Exit");
		exitGame.setBounds(20, 230, 140, 25);
		exitGame.setCursor(new Cursor(Cursor.HAND_CURSOR));

		back.add(newGame);
		back.add(loadGame);
		back.add(exitGame);
		add(back);

		repaint();

		newGame.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (e.getID()) {
				case MouseEvent.MOUSE_CLICKED:
					setVisible(false);
					JFileChooser fileOpen = new JFileChooser("./levels/");
					fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileOpen.setAcceptAllFileFilterUsed(false);
					int value = fileOpen.showDialog(
							MainMenu.this.getContentPane(), "New Game");
					if (value == JFileChooser.APPROVE_OPTION) {
						File archive = fileOpen.getSelectedFile();
						try {
							new GamePlay(archive);
						} catch (IOException exc) {
							JOptionPane.showMessageDialog(
									MainMenu.this.getContentPane(),
									"An error has occurred while loading the file");
							new MainMenu();
						}
						dispose();
					}
					else{
						new MainMenu();
					}
				}
			}
		});
		
		loadGame.addMouseListener(new MouseAdapter(){
        	@Override
            public void mouseClicked(MouseEvent e) {
                switch(e.getID()){
                	case MouseEvent.MOUSE_CLICKED:
                    	
                		Game aux = Game.getInstance();
                    	JFileChooser fileLoad = new JFileChooser("./saveGames");
        				fileLoad.setFileSelectionMode(JFileChooser.FILES_ONLY);
        				fileLoad.setAcceptAllFileFilterUsed(false);
        				int value = fileLoad.showDialog(MainMenu.this.getContentPane(), "Load Game");
        				
        				if (value == JFileChooser.APPROVE_OPTION) {
	        				String loaded = fileLoad.getSelectedFile().toString();
	        				
	        				try {
	    						aux = new SaveandLoadGame().LoadGame(loaded);
	    						new GamePlay(aux);
	    					} catch (ClassNotFoundException e1) {
	    						JOptionPane.showMessageDialog(MainMenu.this.getContentPane(),
	    								"The file was not found, try again");
	    						new MainMenu();
	    					} catch (IOException e1) {
	    						JOptionPane.showMessageDialog(MainMenu.this.getContentPane(),
	    								"An error has occurred while loading the file");
	    						new MainMenu();
	    					}
        				}
        				else{
        					new MainMenu();
        				}
						dispose();
                }
        	}
		});
                	

		exitGame.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (e.getID()) {
				case MouseEvent.MOUSE_CLICKED:
					System.exit(0);
				}

			}
		});

	}

	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu();
	}
	
}
