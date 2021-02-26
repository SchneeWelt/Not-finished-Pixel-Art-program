package myObjects.tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import handler.OnMouseRelease;
import myObjects.hud.screen.Pixel;
import myObjects.hud.screen.Screen;

/**
 * 
 * Dises Werkzeug entspricht dem aufhellungs/verdunklungs tool bei piskel
 * 
 * @author jaapi
 *
 */

class BurnTool extends Tool implements OnMouseRelease
{
	private WorkState workState = WorkState.brighter;

	public BurnTool(Screen screen)
	{
		super(screen);
	}

	@Override
	protected void defineToolId()
	{
		setToolId("burnTool");
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

	@Override
	public void onMouseRelease(MouseEvent e)
	{
		if (workState.equals(WorkState.brighter))
		{
			Point pixelGridLocation = screen.getSelectedPixel();
			if (!isValidGridLocation(pixelGridLocation))
				return;

			Pixel pixel = screen.getPixelAt(pixelGridLocation);
			pixel = brighterPixel(pixel);
		} else if (workState.equals(WorkState.darker))
		{
			Point pixelGridLocation = screen.getSelectedPixel();
			if (!isValidGridLocation(pixelGridLocation))
				return;

			Pixel pixel = screen.getPixelAt(pixelGridLocation);
			pixel = darkerPixel(pixel);
		}
	}

	/**
	 * erhöt die helligkeit des pixels um eins
	 * 
	 * @param
	 * @return
	 */

	private final Pixel brighterPixel(Pixel pixel)
	{
		Color tempColor = pixel.getPixelColor();
		tempColor = tempColor.brighter();
		pixel.setColor(tempColor);
		return pixel;
	}

	/**
	 * veringert die helligkeit des pixels um eins.
	 * 
	 * @param
	 * @return
	 */

	private final Pixel darkerPixel(Pixel pixel)
	{
		Color tempColor = pixel.getPixelColor();
		tempColor = tempColor.darker();
		pixel.setColor(tempColor);
		return pixel;
	}

	public enum WorkState
	{
		darker, brighter;
	}
}
