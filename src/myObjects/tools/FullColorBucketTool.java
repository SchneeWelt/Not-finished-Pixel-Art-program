package myObjects.tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import handler.OnMouseRelease;
import myObjects.hud.screen.Pixel;
import myObjects.hud.screen.Screen;

class FullColorBucketTool extends Tool implements OnMouseRelease
{
	public FullColorBucketTool(Screen screen)
	{
		super(screen);
	}

	@Override
	protected void defineToolId()
	{
		setToolId("fullColorBucketTool");
	}

	@Override
	public void onMouseRelease(MouseEvent e)
	{
		/* bekomme die farbe des pixels, auf den gerade gezeigt wird */
		Point pixelLocation = screen.getSelectedPixel();
		if (!isValidGridLocation(pixelLocation))
			return;

		Color colorToChange = getColor(pixelLocation);

		/* gehe über jeden pixel */
		for (ArrayList<Pixel> pixelList : screen.getPixelArray())
		{
			for (Pixel pixel : pixelList)
			{
				if (pixel == null)
					continue;

				/* vergleiche die Farbe von jedem Pixel mit der colorToChange farbe */
				Color tempColor = pixel.getPixelColor();

				if (tempColor.equals(colorToChange))
				{
					/* setze die farbe diese pixels auf die toolColor */
					pixel.setColor(Tool.getToolColor());
				}
			}
		}
	}

	@Override
	protected void drawPixels()
	{
		/* do nothing */
	}

	@Override
	protected void heighleightPixelAt(Point pixelLocationOnGrid)
	{
		heighlightSmallPixel(pixelLocationOnGrid);
	}

	/**
	 * Gibt die farbe des pixels zurück, auf den die maus gerade zeigt.
	 * 
	 * @return
	 */

	private final Color getColor(Point pixelLocation)
	{
		Pixel pixel = screen.getPixelAt(pixelLocation);
		return pixel.getPixelColor();
	}
}
