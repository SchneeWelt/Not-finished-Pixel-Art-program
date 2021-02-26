package myObjects.background;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import cons.Cons;
import myObjects.Draw;

public class Background implements Draw
{
	private Point location = new Point();
	private Dimension dimension = Cons.getScreenDimension();

	@Override
	public final void draw(Graphics2D graphics)
	{
		graphics.setColor(Color.black);
		graphics.fillRect(location.x, location.y, dimension.width, dimension.height);
	}
}
