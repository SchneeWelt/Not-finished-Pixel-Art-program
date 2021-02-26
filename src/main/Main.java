package main;

import java.awt.Graphics2D;
import java.awt.Point;

import cons.Cons;
import gui.IFrame;
import gui.RepaintControler;
import myObjects.Draw;
import myObjects.background.Background;
import myObjects.hud.ScreenInfoHud;
import myObjects.hud.screen.Screen;
import myObjects.hud.screen.ScreenFactory;
import myObjects.hud.toolHud.ToolHud;
import myObjects.tools.ToolSwitch;

public class Main implements Draw
{
	private ToolHud toolHud = null;
	private ToolSwitch toolSwitch = null;
	private IFrame iFrame = new IFrame();
	private Background background = new Background();
	private Screen screen = ScreenFactory.createDefaultScreen();
	private ScreenInfoHud infoBox = new ScreenInfoHud(screen, new Point(20, 200));

	public Main()
	{
		setup();

		/* game loop */
		while (true)
		{
			sleep();
			draw(iFrame.getIPanelGraphics());
			RepaintControler.getRepaintControler().repaintIPanel(new Point(), Cons.getScreenDimension());
		}
	}

	@Override
	public final void draw(Graphics2D graphics)
	{
		/* background layer */
		background.draw(graphics);

		/* foreground layer */
		screen.draw(graphics);
		infoBox.draw(graphics);
		toolHud.draw(graphics);
		toolSwitch.draw(graphics);
	}

	private final void sleep()
	{
		try
		{
			Thread.sleep((int) Cons.getTickrate());
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private final void setup()
	{
		toolSwitch = new ToolSwitch(screen);
		toolHud = new ToolHud(new Point(20, 20), toolSwitch);
		RepaintControler.initReapaintControler(iFrame);
	}

	public static void main(String[] args)
	{
		new Main();
	}
}
