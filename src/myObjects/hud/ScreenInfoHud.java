package myObjects.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import myObjects.Draw;
import myObjects.hud.screen.Screen;

public class ScreenInfoHud extends Hud implements Draw
{
	private Screen screenObject = null;
	private Point targetPixel = new Point();
	private Dimension gridDimension = new Dimension();

	/**
	 * Die InfoBox zeigt an, wie viele pixel gerade in x und y achse existieren und
	 * auf welchem Pixel sich die Maus gerade befindet
	 */

	public ScreenInfoHud(Screen targetScreen, Point location)
	{
		super(location, new Dimension(63, 30));
		this.screenObject = targetScreen;
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);

		/* calculations */
		getLatestData();

		/* foreground */
		displayMessages(graphics);
	}

	private final void getLatestData()
	{
		gridDimension = screenObject.getGridDimension();
		targetPixel = screenObject.getSelectedPixel();
	}

	private final void displayMessages(Graphics2D graphics)
	{
		graphics.setColor(Color.red);

		int offsetX = 3;
		int offsetY = 5;

		String pixels = "X: " + gridDimension.width + "\n ";
		pixels += "Y: " + gridDimension.height;
		graphics.drawString(pixels, location.x + offsetX, location.y + dimension.height * 1 / 4 + offsetY);

		if (!(targetPixel == null))
		{
			String target = "X: " + targetPixel.x + "\n ";
			target += "Y: " + targetPixel.y;
			graphics.drawString(target, location.x + offsetX, location.y + dimension.height * 3 / 4 + offsetY);
		} else
		{
			String target = "X: ?" + "\n ";
			target += "Y: ?";
			graphics.drawString(target, location.x + offsetX, location.y + dimension.height * 3 / 4 + offsetY);
		}
	}
}