package myObjects.tools;

import java.awt.Point;

import myObjects.hud.screen.Pixel;
import myObjects.hud.screen.Screen;

/**
 * This überschreibt die draw pixel methode. Somit ist die einziege farbe, die
 * this malen kann die farbe null.
 * 
 * @author jaapi
 *
 */

class EraserTool extends Tool
{
	public EraserTool(Screen screen)
	{
		super(screen);
	}

	@Override
	protected void defineToolId()
	{
		setToolId("eraserTool");
	}

	@Override
	protected void drawSmallPixel(Point gridLocation)
	{
		if (!isValidGridLocation(gridLocation))
			return;

		Pixel pixel = screen.getPixelAt(gridLocation);
		pixel.setColor(null);
	}
}
