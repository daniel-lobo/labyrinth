package frontend;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;


import backend.Game;
import backend.cell.Cell;
import backend.cell.Fire;
import backend.cell.FireBox;
import backend.cell.Floor;
import backend.cell.Interruptor;
import backend.cell.Target;
import backend.cell.Wall;
import backend.content.Box;
import backend.content.Player;

public class Draw {

	private final HashMap<Class<?>, String> images = new HashMap<Class<?>, String>();
	private boolean element = true;

	public Draw() throws IOException {
		images.put(Box.class, "resources/block2.png");
		images.put(Floor.class, "resources/cell.png");
		images.put(Player.class, "resources/link.png");
		images.put(Fire.class, "resources/fire.png");
		images.put(Wall.class, "resources/wall.png");
		images.put(Target.class, "resources/mastersword.gif");
		images.put(Interruptor.class, "resources/InterruptorA.gif");

	}

	public Image drawCell(Game board, int row, int col)  {
		try{
			Cell cell = board.getCell(new Point(row, col));
			if (cell instanceof Wall || cell instanceof Fire ) {
				return loadImage(images.get(cell.getClass()));
			}else if(cell instanceof Interruptor){
				if(cell.isAccesible()){
					return loadImage(images.get(cell.getClass()));
				}
				else if(element){
					element = false;
					return loadImage(images.get(Floor.class));
				}
				else{
					element = true;
					return loadImage(images.get(cell.getContent().getClass()));
				}
			}
			else if(cell instanceof Target){
				if(cell.isAccesible()){
					if(board.isInterruptorActive()){
						return loadImage(images.get(cell.getClass()));
					}
					else
						return loadImage(images.get(Floor.class));
				}
				else{
					if(element){
						element = false;
						return loadImage(images.get(Floor.class));
					}
				else{
					element = true;
					return loadImage(images.get(cell.getContent().getClass()));
			}
		}
	}
		else if (cell instanceof FireBox) {
			if (!cell.isAccesible()) {
				if(element){
					element = false;
					return colorize(loadImage(images.get(Box.class)), new Color(
							100, 100, 100));
				}
				else
				{
					element = true;
					return loadImage(images.get(cell.getContent().getClass()));
				}
			} else {
				return colorize(loadImage(images.get(Box.class)), new Color(
						100, 100, 100));
			}
		} else {
			if (cell.isAccesible()) {
				return loadImage(images.get(cell.getClass()));
			} else {
				 if(cell.getContent() != null && element){
					 element=false;
				 return loadImage(images.get(cell.getClass()));
				 }
				element = true;
				return loadImage(images.get(cell.getContent().getClass()));
			}
		}
		}
		catch(IOException e){
			throw new IllegalStateException();
		}

	}
	
	public Image drawFloor()  {
		try {
			return loadImage(images.get(Floor.class));
		} catch (IOException e) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Carga una imagen y retorna una instancia de la misma. Si hay algun
	 * problema al leer el archivo lanza una excepcion.
	 */
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {
			return ImageIO.read(new File(fileName));
		} else {
			return ImageIO.read(stream);
		}
	}

	/**
	 * Dada una imagen en escala de grises, la tinie con el color indicado.
	 */
	public static Image colorize(Image image, Color color) {
		BufferedImage result = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		result.getGraphics().drawImage(image, 0, 0, null);

		for (int x = 0; x < image.getWidth(null); x++) {
			for (int y = 0; y < image.getHeight(null); y++) {
				Color c = new Color(result.getRGB(x, y), true);

				if (c.getAlpha() != 0) {
					double r = c.getGreen() / 255.0;
					Color c2 = new Color((int) (r * color.getRed()),
							(int) (r * color.getGreen()),
							(int) (r * color.getBlue()));
					result.setRGB(x, y, c2.getRGB());
				}
			}
		}
		return result;

	}

}
