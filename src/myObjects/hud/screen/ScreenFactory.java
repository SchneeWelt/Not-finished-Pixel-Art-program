package myObjects.hud.screen;

import java.awt.Dimension;
import java.awt.Point;

import cons.Cons;

public class ScreenFactory
{
	public static final Screen createCoustomScreen(Point screenStartLocation, Dimension screenDimension)
	{
		Dimension gridDimension = new Dimension(20, 16);
		return new Screen(screenStartLocation, screenDimension, gridDimension);
	}

	public static final Screen createDefaultScreen()
	{
		int x = 240;
		int y = 25;

		int width = Cons.getScreenDimension().width - x - 100;
		int height = Cons.getScreenDimension().height - y - 100 + 1;

		return createCoustomScreen(new Point(x, y), new Dimension(width, height));
	}
}
