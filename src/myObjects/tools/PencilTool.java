package myObjects.tools;

import myObjects.hud.screen.Screen;

class PencilTool extends Tool
{
	public PencilTool(Screen screen)
	{
		super(screen);
	}

	@Override
	protected final void defineToolId()
	{
		setToolId("pencilTool");
	}
}
