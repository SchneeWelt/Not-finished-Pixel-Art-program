package cons;

import java.awt.Dimension;
import java.awt.Point;

import handler.KeyEventHandler;
import handler.MouseEventHandler;

public class Cons
{
	/* mouse and keyboard events */
	private static KeyEventHandler keyEventHandler = new KeyEventHandler();
	private static MouseEventHandler mouseEventHandler = new MouseEventHandler();

	/* mouse data */
	private static boolean mousePressed = false;
	private static Point mouseLocation = new Point();
	
	/* system */
	private static final double tickRate = 1000 / 30;
	private static final Dimension SCREEN_DIMENSION = new Dimension(900, 600);

	public static final boolean isMousePressed()
	{
		return mousePressed;
	}

	public static final void setMousePressed(boolean mousePressed)
	{
		Cons.mousePressed = mousePressed;
	}

	public static final Point getMouseLocation()
	{
		return mouseLocation;
	}

	public static MouseEventHandler getMouseEventHandler()
	{
		return mouseEventHandler;
	}

	public static final KeyEventHandler getKeyEventHandler()
	{
		return keyEventHandler;
	}

	public static final double getTickrate()
	{
		return tickRate;
	}

	public static final void setMouseLocation(Point mouseLocation)
	{
		Cons.mouseLocation = mouseLocation;
	}

	public static final Dimension getScreenDimension()
	{
		return SCREEN_DIMENSION;
	}
}
