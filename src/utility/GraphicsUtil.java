package utility;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class GraphicsUtil
{
	public static final void fillRect(Graphics2D graphics, Point location, Dimension dimension)
	{
		graphics.fillRect(location.x, location.y, dimension.width, dimension.height);
	}

	public static final void drawRect(Graphics2D graphics, Point location, Dimension dimension)
	{
		graphics.drawRect(location.x, location.y, dimension.width, dimension.height);
	}

	public static final void drawImage(Graphics2D graphics, BufferedImage image, Point location, Dimension dimension)
	{
		if (dimension == null)
		{
			graphics.drawImage(image, location.x, location.y, null);
			return;
		}

		graphics.drawImage(image, location.x, location.y, dimension.width, dimension.height, null);
	}
}
