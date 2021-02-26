package myObjects.hud.box;

import java.awt.Graphics2D;
import java.awt.Point;

import myObjects.tools.Tool;
import utility.GraphicsUtil;

/**
 * This is eine Box, die die farbe, des aktuel ausgewälten tools anzeigt.
 * 
 * @author jaapi
 *
 */

public class CurentColorDisplayerBox extends Box
{
	public CurentColorDisplayerBox(Point location)
	{
		super(location, null);
	}

	@Override
	protected void displayBackground(Graphics2D graphics)
	{
		if (Tool.getToolColor() == null)
			return;
		
		graphics.setColor(Tool.getToolColor());
		GraphicsUtil.fillRect(graphics, location, dimension);
	}
}
