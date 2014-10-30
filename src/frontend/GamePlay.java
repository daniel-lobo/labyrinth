package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import manager.Parser;
import backend.Game;
import backend.Cardinal;
import backend.SaveandLoadGame;
import backend.State;
import exceptions.ParsingException;
import frontend.MainMenu;

public class GamePlay extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final int CELL_SIZE = 30;
	private Game game;
	private final GamePanel gp;
	
	public GamePlay(File archive) throws IOException {
		this(createFromArchive(archive));
	}
		
	public GamePlay(Game game){
		this.game = game;
		setTitle("Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		createMenuBar();
		setSize (game.getWidth()*CELL_SIZE + 20,game.getHeight()*CELL_SIZE + 70 );
		gp = new GamePanel(game, this);
		handleInput();
		add(gp);
		gp.drawBoard();
		centerScreen();
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Game");
		menuBar.add(menu);

		this.add(menuBar);
		JMenuItem save = new JMenuItem("Save");
		JMenuItem quit = new JMenuItem("Exit");
		menu.add(save);
		menu.add(quit);
		setJMenuBar(menuBar);
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveGame();
			}
		});
	}

	private void centerScreen() {
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(300, 300);
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2
				- getHeight() / 2);
	}

	private void handleInput() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(final KeyEvent e) {
				if(game.getState().equals(State.PLAYING)){
				
					switch (e.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							game.move(Cardinal.WEST);
							break;
						case KeyEvent.VK_RIGHT:
							game.move(Cardinal.EAST);
							break;
						case KeyEvent.VK_UP:
							game.move(Cardinal.NORTH);
							break;
						case KeyEvent.VK_DOWN:
							game.move(Cardinal.SOUTH);
							break;
					}
					gp.drawBoard();
					playerWon();
					playerLost();
				}
			}
		});
	}
	
	private void playerLost() {
		if(game.getState().equals(State.LOSE)){
			new EndScreen(this, "YOU LOSE").setVisible(true);
		}
		
	}

	private void playerWon() {
		if(game.getState().equals(State.WIN)){
			new EndScreen(this, "YOU WIN").setVisible(true);
		}
		
	}
	
	private void saveGame() {
	        String name;
	        name = (String) JOptionPane.showInputDialog(null, "Ingrese un nombre",
	        "Guardar Partida",JOptionPane.OK_CANCEL_OPTION);
	        if(name != null) {
	        	File saveFolder = new File("saveGames");
	        	saveFolder.mkdir();
	        	try{
	        		new SaveandLoadGame().SaveGame(game,"saveGames/" + name);
	        } catch (IOException e) {
				new ErrorWindow("Error al guardar partida", this).setVisible(true);
				new MainMenu();
	        }
	     }
	}
	
	private static Game createFromArchive(File archive)throws IOException{
		try {
			return (new Parser()).parse(archive);
		} catch (ParsingException exc) {
			JOptionPane.showMessageDialog(null,
					"El mapa seleccionado es invalido", "Error",
					JOptionPane.ERROR_MESSAGE);
					throw new IOException();
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null,
					"El mapa seleccionado es invalido", "Error",
					JOptionPane.ERROR_MESSAGE);
				throw new IOException();
				
		}
	}
	
	
}