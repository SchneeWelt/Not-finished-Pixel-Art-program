package myObjects.hud.toolHud;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import myObjects.Draw;
import myObjects.hud.Hud;
import myObjects.tools.ToolSwitch;

public class ToolHud extends Hud implements Draw
{
	private ToolSwitch toolSwitch = null;
	private ToolColorSelector toolColorSelector = null;
	private PencilWidthButtons pencilWidthButtons = null;

	public ToolHud(Point location, ToolSwitch toolSwitch)
	{
		super(location, new Dimension(125, 400));

		this.toolSwitch = toolSwitch;
		toolColorSelector = new ToolColorSelector(getToolColorSelectorLocation());
		pencilWidthButtons = new PencilWidthButtons(getPencilWidthButtonLocation());
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);
		toolColorSelector.draw(graphics);
		pencilWidthButtons.draw(graphics);
	}

	@Override
	public void onDrag()
	{
		super.onDrag();
		toolColorSelector = null;
		pencilWidthButtons = null;
		toolColorSelector = new ToolColorSelector(getToolColorSelectorLocation());
		pencilWidthButtons = new PencilWidthButtons(getPencilWidthButtonLocation());
	}

	private final Point getToolColorSelectorLocation()
	{
		return new Point(location.x + 5, location.y + 40);
	}

	private final Point getPencilWidthButtonLocation()
	{
		return new Point(location.x + 5, location.y + 5);
	}
}
