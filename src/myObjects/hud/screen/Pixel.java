package myObjects.hud.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import cons.Cons;
import myObjects.Draw;
import utility.GraphicsUtil;

public class Pixel implements Draw
{
	private boolean hightlightPixel = false;

	private Color pixelColor = null;
	private Point location = new Point(); // die position relativ zum ursprung
	private Dimension pixelDimension = new Dimension(20, 20);

	public Pixel(Point locationOnScreen, Dimension pixelDimension)
	{
		location.setLocation(locationOnScreen);
		this.pixelDimension.setSize(pixelDimension);
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		displayPixel(graphics);
		highlightPixel(graphics);
	}

	/**
	 * Diese methode sorgt dafür, dass this für einen tick gehighlightet wird
	 * 
	 */

	private final void highlightPixel(Graphics2D graphics)
	{
		if (!isHightlightPixel())
			return;

		graphics.setColor(new Color(63, 131, 191, 90));
		GraphicsUtil.fillRect(graphics, location, pixelDimension);

		setHightlightPixel(false);
	}

	private final void displayPixel(Graphics2D graphics)
	{
		if (isNoPixelColor())
			return;

		graphics.setColor(pixelColor);
		GraphicsUtil.fillRect(graphics, location, pixelDimension);
	}

	public final boolean isNoPixelColor()
	{
		return pixelColor == null;
	}

	public final Color getPixelColor()
	{
		return pixelColor;
	}

	public final Point getLocation()
	{
		return location;
	}

	public final void setLocation(Point location)
	{
		this.location = location;
	}

	/**
	 * setzt den Farbwert dieses Pixels dauerhaft auf den input farbparameter
	 * 
	 * @param pixelColor
	 */

	public final void setColor(Color pixelColor)
	{
		this.pixelColor = pixelColor;
	}

	public final void setHightlightPixel(boolean hightlightPixel)
	{
		this.hightlightPixel = hightlightPixel;
	}

	public final Dimension getPixelDimension()
	{
		return pixelDimension;
	}

	public final void setPixelDimension(Dimension pixelDimension)
	{
		this.pixelDimension = pixelDimension;
	}

	/**
	 * Ist true, wenn die maus gerade auf diesen Pixel zeigt
	 * 
	 * @return
	 */

	public final boolean isMouseIntersection()
	{
		return new Rectangle(location, pixelDimension).contains(Cons.getMouseLocation());
	}

	private final boolean isHightlightPixel()
	{
		return hightlightPixel;
	}
}
