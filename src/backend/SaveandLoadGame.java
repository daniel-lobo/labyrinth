package backend;

import java.io.*;

import javax.swing.JOptionPane;

public class SaveandLoadGame {

	public SaveandLoadGame() {
	}

	public void SaveGame(Game game, String file)
			throws FileNotFoundException, IOException {
		try {
			ObjectOutputStream newFile = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			newFile.writeObject(game);
			newFile.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Unable To Save");
			e.printStackTrace();
		}
	}


	public Game LoadGame(String file) throws FileNotFoundException,IOException, ClassNotFoundException {
		try {
			ObjectInputStream newfile = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			Game game = (Game) newfile.readObject();
			return game;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to Load - Unacceptable File Type");
			throw new IOException();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Unable to Load - Unacceptable File Type");
			throw new IOException();
			}
	}

}
