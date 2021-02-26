package myObjects.hud.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import myObjects.Draw;
import myObjects.hud.Hud;
import myObjects.hud.box.dragBox.OnDrag;
import utility.GraphicsUtil;
import utility.ImageLoader;

public class Screen extends Hud implements Draw, OnDrag
{
	private Dimension gridDimension = new Dimension();
	private ArrayList<ArrayList<Pixel>> pixelArray = null;
	private Dimension pixelDimension = new Dimension(20, 20);
	private BufferedImage backgroundImage = ImageLoader.loadImage("res/screenBackground.png");

	protected Screen(Point location, Dimension dimension, Dimension gridDimension)
	{
		super(location, dimension);

		this.gridDimension.setSize(gridDimension);
		this.pixelDimension = getPixelDimension(gridDimension);

		createPixelArray();
		fillPixelArray();
		pack();
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);

		/* background */
		displayScreenBackground(graphics);

		/* foreground */
		drawPixelArray(graphics);
		displayScreenBorder(graphics);

		Point gridLocation = getSelectedPixel();
		if (gridLocation == null)
			return;
	}

	@Override
	public void onDrag()
	{
		realignPixels();
	}

	private final void displayScreenBackground(Graphics2D graphics)
	{
		GraphicsUtil.drawImage(graphics, backgroundImage, location, dimension);
	}

	private final void drawPixelArray(Graphics2D graphics)
	{
		for (ArrayList<Pixel> pixelList : pixelArray)
		{
			for (Pixel pixel : pixelList)
			{
				if (pixel == null)
					continue;

				pixel.draw(graphics);
			}
		}
	}

	private final void displayScreenBorder(Graphics2D graphics)
	{
		graphics.setColor(Color.blue);
		GraphicsUtil.drawRect(graphics, location, dimension);
	}

	private final void createPixelArray()
	{
		pixelArray = new ArrayList<ArrayList<Pixel>>();
	}

	private final void fillPixelArray()
	{
		int amountOfPixelsX = dimension.width / pixelDimension.width;
		int amountOfPixelsY = dimension.height / pixelDimension.height;

		for (int indexY = 0; indexY < amountOfPixelsY; indexY++)
		{
			/* füge zeile ein */
			pixelArray.add(new ArrayList<Pixel>());

			/* füge pixel in die zeile ein */
			for (int indexX = 0; indexX < amountOfPixelsX; indexX++)
			{
				int pX = pixelDimension.width * indexX + location.x;
				int pY = pixelDimension.height * indexY + location.y;

				pixelArray.get(indexY).add(new Pixel(new Point(pX, pY), pixelDimension));
			}
		}
	}

	/**
	 * ändert die screenDimension so um, dass jeder pixel natlos reinpasst. Sollte
	 * als allerletze setup funktion aufgerufen werden.
	 */
	private final void pack()
	{
		int lastY = pixelArray.size() - 1;
		int lastX = pixelArray.get(lastY).size() - 1;
		Pixel pixel = pixelArray.get(lastY).get(lastX);

		int newWidth = pixel.getLocation().x + pixel.getPixelDimension().width - location.x;
		int newHeight = pixel.getLocation().y + pixel.getPixelDimension().height - location.y;
		dimension.setSize(newWidth, newHeight);
	}

	private final void realignPixels()
	{
		int indexX = 0;
		int indexY = 0;

		for (ArrayList<Pixel> pixelList : pixelArray)
		{
			for (Pixel pixel : pixelList)
			{
				if (pixel == null)
					continue;

				int newX = pixelDimension.width * indexX + location.x;
				int newY = pixelDimension.height * indexY + location.y;

				pixel.setLocation(new Point(newX, newY));

				indexX++;
			}

			indexX = 0;
			indexY++;
		}
	}

	private final Dimension getPixelDimension(Dimension numberOfPixels)
	{
		Dimension pixelDimension = new Dimension();

		pixelDimension.width = dimension.width / numberOfPixels.width;
		pixelDimension.height = dimension.height / numberOfPixels.height;

		return pixelDimension;
	}

	/**
	 * gibt die pixel grid location zurück, auf den die maus gerade zeigt. Wenn die
	 * Maus gerade auf keinen Pixel zeigt, wird null zurückgegeben. Der Pixel erste
	 * pixel auf der x achse und der zweite auf der y achse hat zum Beispiel den
	 * Wert 0,1
	 * 
	 * @return
	 */

	public final Point getSelectedPixel()
	{
		Point selectedPixelLocation = null;

		for (ArrayList<Pixel> pixelList : pixelArray)
		{
			for (Pixel pixel : pixelList)
			{
				if (pixel == null)
					continue;

				if (!pixel.isMouseIntersection())
					continue;

				int x = pixel.getLocation().x - this.location.x;
				int y = pixel.getLocation().y - this.location.y;
				Point locationRelativToScreen = new Point(x, y);

				x = locationRelativToScreen.x / pixelDimension.width;
				y = locationRelativToScreen.y / pixelDimension.height;
				selectedPixelLocation = new Point(x, y);
			}
		}

		return selectedPixelLocation;
	}
	
	/**
	 * Gibt die liste zurück, in der sich alle Pixel des Screen Objectes 
	 * befinden.
	 * 
	 * @return
	 */
	
	public final ArrayList<ArrayList<Pixel>> getPixelArray()
	{
		return pixelArray;
	}

	/**
	 * gibt den pixel zurück, der auf dem punkt liegt, wo die pixelGridLocation
	 * hinzeigt. Hat ein Screen 20 mal 16 Pixel, so befindet sich der erste Pixel
	 * auf der pixeGridLocation 0,0. Der Pixel, auf dem sich gerade die maus
	 * befindet kann mittels des befehls getSelectedPixel erhalten werden. Da wird
	 * dann genau die gewünschte gridLocation dieses Pixels zurückgegeben.
	 * 
	 * Der Wert pixelGridLocation kann dann null sein, wenn der Pixel der
	 * dazugehörigen pixelGridLocation nicht existiert. Sagen wir der screen hat x =
	 * 20 pixel. Jetzt hätte ich gerne den 39 Pixel also x = 39. In diesem Fall wird
	 * hier ein Nullpointer geworfen, da der Pixel nicht existiert. Das Maximum bei
	 * x = 20 währe hier ja schließlich 19.
	 * 
	 * @param pixelGridLocation
	 * @return
	 */

	public final Pixel getPixelAt(Point pixelGridLocation)
	{
		return pixelArray.get(pixelGridLocation.y).get(pixelGridLocation.x);
	}

	/**
	 * Gibt eine Dimension zurück, die für die Anzahl an pixeln nach rechts (width)
	 * und nach untent (height) steht. Ein grid mit 16 mal 16 Pixeln sähe also so
	 * aus: Dimension(16, 16). Beachte, dass das grid dann die Pixel von 0 bis 15
	 * besitzt.
	 * 
	 * 
	 * @return
	 */

	public final Dimension getGridDimension()
	{
		return gridDimension;
	}
}
