package myObjects.hud.box;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import cons.Cons;
import myObjects.Draw;
import utility.GraphicsUtil;

/**
 * Die SuperKlasse der Boxen. This stellt einen Positions und größen Vektor,
 * sowie ein Image, dass auf der Box gemalt werden kann. Ist das image Null, so
 * wird kein Bild gemalt. Eine Box hat eine blaue border und einen grauen
 * hintergrund. Die Dimension einer Box ist immer 20 mal 20 Pixel. Es sei denn,
 * dieser Wert wird überschrieben. Dies sollte aber nur getan werden, wenn nicht
 * unbedingt gewollt.
 * 
 * @author jaapi
 *
 */

public abstract class Box implements Draw
{
	private BufferedImage image = null;
	protected Point location = new Point();
	protected Dimension dimension = new Dimension();

	public Box(Point location, BufferedImage image)
	{
		this.image = image;
		this.location.setLocation(location);
		this.dimension.setSize(new Dimension(20, 20));
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		/* draw background */
		displayBackground(graphics);

		/* draw foreground */
		displayImage(graphics);
		displayBorder(graphics);
	}

	protected final boolean isMouseIntersection()
	{
		return new Rectangle(location, dimension).contains(Cons.getMouseLocation());
	}

	protected final void setImage(BufferedImage image)
	{
		this.image = image;
	}

	protected void displayBackground(Graphics2D graphics)
	{
		graphics.setColor(Color.gray);
		GraphicsUtil.fillRect(graphics, location, dimension);
	}

	private final void displayImage(Graphics2D graphics)
	{
		if (image == null)
			return;

		GraphicsUtil.drawImage(graphics, image, location, dimension);
	}

	protected void displayBorder(Graphics2D graphics)
	{
		graphics.setColor(Color.blue);
		GraphicsUtil.drawRect(graphics, location, dimension);
	}
}
