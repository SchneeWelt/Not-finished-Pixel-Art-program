package myObjects.hud.box.dragBox;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import cons.Cons;
import myObjects.hud.box.Box;

/**
 * Eine DragBox ist eine Box, die nicht wie alle anderen den DimensionsMa�stab
 * von 20 mal 20 Pixeln (Wert k�nnte milerweile veraltet sein) besitz. This
 * bewegt sich selber und das object dessen die locationRefernce geh�rt, sobald
 * die maus �bder this gehalten wird und gedr�ckt wird.
 * 
 * @author jaapi
 *
 */

public class DragBox extends Box
{
	boolean draggingBox = false;

	private Point locationReference = null;
	private ArrayList<OnDrag> onDragList = new ArrayList<OnDrag>();

	public DragBox(Point dragBoxLocation, Dimension dimension, Point locationReference, OnDrag onDrag)
	{
		super(dragBoxLocation, null);

		onDragList.add(onDrag);
		super.dimension.setSize(dimension);
		this.locationReference = locationReference;
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);

		passLocation();

		if (isDraggingBox())
			triggerOnDrag();
	}

	private final void triggerOnDrag()
	{
		for (OnDrag onDrag : onDragList)
		{
			if (onDrag == null)
				continue;

			onDrag.onDrag();
		}
	}

	private final void passLocation()
	{
		if (!Cons.isMousePressed())
		{
			setDraggingBox(false);
			return;
		}

		if (!isMouseIntersection())
			if (!isDraggingBox())
				return;

		setDraggingBox(true);

		int x = Cons.getMouseLocation().x - dimension.width / 2;
		int y = Cons.getMouseLocation().y - dimension.height / 2;
		location = new Point(x, y);

		y += dimension.height;
		locationReference.setLocation(new Point(x, y));
	}

	private final boolean isDraggingBox()
	{
		return draggingBox;
	}

	private final void setDraggingBox(boolean draggingBox)
	{
		this.draggingBox = draggingBox;
	}
}
