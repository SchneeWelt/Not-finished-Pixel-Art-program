package myObjects.tools;

import java.awt.Point;

import myObjects.hud.screen.Screen;

class ColorBucketTool extends FullColorBucketTool
{
	public ColorBucketTool(Screen screen)
	{
		super(screen);
	}

	@Override
	protected void defineToolId()
	{
		setToolId("colorBucketTool");
	}

	@Override
	protected void drawPixels()
	{
		super.drawPixels();
	}

	@Override
	protected void heighleightPixelAt(Point pixelLocationOnGrid)
	{
		super.heighleightPixelAt(pixelLocationOnGrid);
	}
}
