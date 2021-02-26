package myObjects.hud.toolHud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import myObjects.Draw;
import myObjects.hud.box.Box;
import myObjects.hud.box.buttonBox.ButtonBox;
import myObjects.hud.box.buttonBox.OnButtonInteraction;
import myObjects.tools.Tool;
import myObjects.tools.Tool.ToolWidth;
import utility.GraphicsUtil;
import utility.ImageLoader;

class PencilWidthButtons implements Draw, OnButtonInteraction
{
	private ButtonBox small = null;
	private ButtonBox large = null;
	private ButtonBox medium = null;
	private Point location = new Point();
	private Dimension dimension = new Dimension();
	private CurrentPencilSizeDisplayerBox currentPencilSizeDisplayerBox = null;

	public PencilWidthButtons(Point location)
	{
		this.location.setLocation(location);
		this.dimension.setSize(new Dimension(115, 30));

		small = createSmall();
		large = createLarge();
		medium = createMedium();
		currentPencilSizeDisplayerBox = createCurrentPencilSizeDisplayerBox();
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		/* background */
		displayBackground(graphics);

		/* foreground */
		small.draw(graphics);
		large.draw(graphics);
		medium.draw(graphics);
		currentPencilSizeDisplayerBox.draw(graphics);
	}

	@Override
	public void onButtonInteraction(String buttonId)
	{
		if (buttonId.equals(small.getButtonId()))
			Tool.setToolWidth(ToolWidth.small);
		else if (buttonId.equals(medium.getButtonId()))
			Tool.setToolWidth(ToolWidth.medium);
		else if (buttonId.equals(large.getButtonId()))
			Tool.setToolWidth(ToolWidth.large);
	}

//	/**
//	 * setzt this zunächst auf die neue Position, in dem this.location ändert und
//	 * ruft dann die updateComponent methode auf, die alle objecte auf this neu
//	 * ausrichtet.
//	 * 
//	 * @param location
//	 */
//
//	public final void changeLocation(Point location)
//	{
//		setLocation(location);
//		small = createSmall();
//		large = createLarge();
//		medium = createMedium();
//		currentPencilSizeDisplayerBox = createCurrentPencilSizeDisplayerBox();
//	}

	private class CurrentPencilSizeDisplayerBox extends Box
	{
		public CurrentPencilSizeDisplayerBox(Point location)
		{
			super(location, null);
			assignValue();
		}

		@Override
		public void draw(Graphics2D graphics)
		{
			super.draw(graphics);
			assignValue();
		}

		private final void assignValue()
		{
			if (Tool.getToolWidth().equals(ToolWidth.small))
				setImage(ImageLoader.loadImage("res/small.png"));
			else if (Tool.getToolWidth().equals(ToolWidth.medium))
				setImage(ImageLoader.loadImage("res/medium.png"));
			else if (Tool.getToolWidth().equals(ToolWidth.large))
				setImage(ImageLoader.loadImage("res/large.png"));
		}
	}

	private final void displayBackground(Graphics2D graphics)
	{
		graphics.setColor(Color.white);
		GraphicsUtil.fillRect(graphics, new Point(this.location.x, location.y), dimension);
	}

	private final ButtonBox createSmall()
	{
		BufferedImage image = ImageLoader.loadImage("res/small.png");
		return new ButtonBox(new Point(this.location.x + 5, this.location.y + 5), this, image, "small");
	}

	private final ButtonBox createMedium()
	{
		BufferedImage image = ImageLoader.loadImage("res/medium.png");
		return new ButtonBox(new Point(this.location.x + 30, this.location.y + 5), this, image, "medium");
	}

	private final ButtonBox createLarge()
	{
		BufferedImage image = ImageLoader.loadImage("res/large.png");
		return new ButtonBox(new Point(this.location.x + 55, this.location.y + 5), this, image, "large");
	}

	private final CurrentPencilSizeDisplayerBox createCurrentPencilSizeDisplayerBox()
	{
		return new CurrentPencilSizeDisplayerBox(new Point(this.location.x + 90, this.location.y + 5));
	}
}