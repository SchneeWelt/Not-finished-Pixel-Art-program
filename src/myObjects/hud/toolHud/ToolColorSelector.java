package myObjects.hud.toolHud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import myObjects.Draw;
import myObjects.hud.box.ColorSelector;
import myObjects.hud.box.CurentColorDisplayerBox;
import utility.GraphicsUtil;

/**
 * @author jaapi
 *
 */

public class ToolColorSelector implements Draw
{
	private int offsetX = 5;
	private int offsetY = 5;

	private Point location = new Point();
	private Dimension dimension = new Dimension();
	private ColorSelector colorSelectorBox = null;
	private CurentColorDisplayerBox curentColorDisplayerBox = null;

	public ToolColorSelector(Point location)
	{
		this.location = location;
		this.dimension.setSize(new Dimension(115, 30));

		colorSelectorBox = createColorSelectorBox();
		curentColorDisplayerBox = createCurentColorDisplayerBox();
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		displayBackground(graphics);
		colorSelectorBox.draw(graphics);
		curentColorDisplayerBox.draw(graphics);
	}

	private final void displayBackground(Graphics2D graphics)
	{
		graphics.setColor(Color.white);
		GraphicsUtil.fillRect(graphics, this.location, this.dimension);
	}

	private final CurentColorDisplayerBox createCurentColorDisplayerBox()
	{
		Point curentColorDisplayerBoxLocation = new Point(this.location.x + this.dimension.width - offsetX - 20,
				this.location.y + offsetY);
		return new CurentColorDisplayerBox(curentColorDisplayerBoxLocation);
	}

	private final ColorSelector createColorSelectorBox()
	{
		Point colorSelectorBoxLocation = new Point(this.location.x + offsetX, this.location.y + offsetY);
		return new ColorSelector(colorSelectorBoxLocation);
	}
}