package myObjects.tools;

import myObjects.hud.screen.Screen;

public class ShapeSelectionTool extends Tool
{

	public ShapeSelectionTool(Screen screen)
	{
		super(screen);
	}

	@Override
	protected void defineToolId()
	{
		setToolId("shapeSelectionTool");
	}
}
