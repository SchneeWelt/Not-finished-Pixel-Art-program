package myObjects.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import myObjects.Draw;
import myObjects.hud.box.dragBox.DragBox;
import myObjects.hud.box.dragBox.OnDrag;
import utility.GraphicsUtil;

public abstract class Hud implements Draw, OnDrag
{
	private DragBox dragBox = null;
	protected Point location = new Point();
	protected Dimension dimension = new Dimension();

	public Hud(Point location, Dimension dimension)
	{
		this.dimension.setSize(dimension);
		this.location.setLocation(location);

		dragBox = createDragBox();
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		/* background */
		displayHudBackground(graphics);

		/* foreground */
		dragBox.draw(graphics);
		displayHudBorder(graphics);
	}

	@Override
	public void onDrag()
	{

	}

	protected void displayHudBorder(Graphics2D graphics)
	{
		graphics.setColor(Color.blue);
		GraphicsUtil.drawRect(graphics, location, dimension);
	}

	protected void displayHudBackground(Graphics2D graphics)
	{
		graphics.setColor(Color.gray);
		GraphicsUtil.fillRect(graphics, location, dimension);
	}

	private final DragBox createDragBox()
	{
		Dimension dragBoxDimension = new Dimension(dimension.width, 15);
		Point dragBoxLocation = new Point(location.x, location.y - dragBoxDimension.height);

		return new DragBox(dragBoxLocation, dragBoxDimension, location, this);
	}
}
