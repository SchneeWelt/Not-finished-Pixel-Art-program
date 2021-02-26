package myObjects.tools;

import java.awt.Graphics2D;
import java.awt.Point;

import cons.Cons;
import myObjects.hud.screen.Pixel;
import myObjects.hud.screen.Screen;

class ColorPickerTool extends Tool
{
	public ColorPickerTool(Screen screen)
	{
		super(screen);
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);
		pickColor();
	}

	@Override
	protected final void drawPixels()
	{
		/* eine pippetet malt nicht! */
	}

	/**
	 * es kann nur ein einzieger pixel geheileighted werden, egal, was die toolWidth
	 * gerade sagt.
	 */

	@Override
	protected void heighleightPixelAt(Point pixelLocationOnGrid)
	{
		heighlightSmallPixel(pixelLocationOnGrid);
	}

	protected void pickColor()
	{
		if (!Cons.isMousePressed())
			return;

		Point pixelGridLocation = screen.getSelectedPixel();

		if (!isValidGridLocation(pixelGridLocation))
			return;

		Pixel pixel = screen.getPixelAt(pixelGridLocation);
		Tool.setToolColor(pixel.getPixelColor());
	}

	@Override
	protected void defineToolId()
	{
		setToolId("colorPickerTool");
	}
}
