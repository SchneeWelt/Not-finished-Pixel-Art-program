package myObjects.hud.box;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JColorChooser;

import myObjects.hud.button.Button;
import myObjects.hud.button.ButtonConnector;
import myObjects.tools.Tool;

/**
 * Die ColorselectorBox ist eine Box, die wenn man auf sie klickt einen
 * colorselector dialog öffnet. Ich habe fürs erste den von Java genommen. Der
 * Resultierende Farbwert wird dann der Super Klasse Tool zugewiesen. Der letzte
 * ausgewählte farbwert wird solange gespeicher, bis ein neueerer erzeugt wird.
 * 
 * @author jaapi
 *
 */

public class ColorSelector extends Box implements ButtonConnector
{
	private Button button = null;
	private Color lastColor = null;

	public ColorSelector(Point location)
	{
		super(location, null);
		button = createButton();
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);
		button.draw(graphics);
	}

	@Override
	public void onButtonPress(String buttonId)
	{
	}

	@Override
	public void onButtonRelease(String buttonId)
	{
		Color tempColor = JColorChooser.showDialog(null, "Choose a Color", Color.blue);
		
		setLastColor(tempColor);
		
		if (tempColor == null)
			return;
		
		assignLastColor(tempColor);
	}

	private final void assignLastColor(Color color)
	{
		Tool.setToolColor(color);
	}

	private final Button createButton()
	{
		return new Button(location, dimension, "btn1", this);
	}

	private final void setLastColor(Color lastColor)
	{
		this.lastColor = lastColor;
	}

	public final Color getLastColor()
	{
		return lastColor;
	}
}
