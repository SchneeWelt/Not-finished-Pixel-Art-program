package myObjects.hud.box.buttonBox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import cons.Cons;
import handler.OnMousePress;
import handler.OnMouseRelease;
import myObjects.hud.box.Box;
import utility.GraphicsUtil;

/**
 * 
 * This erbt von Box. This überschreibt sämtliche grafischen Funktionen, so dass
 * nur noch ein Bild auf dem Button gemalt werden kann. Für eine ButtonBox gibt
 * es also keinen Hintergrund und keine Border. Zumindest werden diese nicht
 * gemalt. Diese Entscheidung habe ich getroffen, damit auf anderen Boxen eine
 * ButtonBox gesetz werden kann. Diese ist dann unsichtbar, die Box, die die
 * ButtonBox verwendet hat nun jedoch einen Button, auf den gedrückt werden
 * kann.
 * 
 * @author jaapi
 *
 */

@Deprecated
public class ButtonBox extends Box implements OnMouseRelease, OnMousePress
{
	private boolean buttonPressed = false;

	private String buttonId = "";
	private OnButtonInteraction onButtonInteractionRefference = null;

	public ButtonBox(Point location, OnButtonInteraction onButtonInteractionRefference, BufferedImage image,
			String buttonId)
	{
		super(location, image);

		this.buttonId = buttonId;
		this.onButtonInteractionRefference = onButtonInteractionRefference;

		/* connect with mouse event stream */
		Cons.getMouseEventHandler().addOnMousePress(this);
		Cons.getMouseEventHandler().addOnMouseRelease(this);
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		super.draw(graphics);
		sendVisualFeedback(graphics);
	}

	@Override
	public void onMouseRelease(MouseEvent e)
	{
		if (!isMouseIntersection())
			return;

		triggerOnButtonInteraction();
		setButtonPressed(false);
	}

	@Override
	public void onMousePress(MouseEvent e)
	{
		if (!isMouseIntersection())
			return;

		setButtonPressed(true);
	}

	@Override
	protected final void displayBackground(Graphics2D graphics)
	{
	}

	@Override
	protected final void displayBorder(Graphics2D graphics)
	{
	}

	private final void sendVisualFeedback(Graphics2D graphics)
	{
		if (!isButtonPressed())
			return;

		graphics.setColor(new Color(200, 200, 200));
		GraphicsUtil.fillRect(graphics, location, dimension);
	}

	public final String getButtonId()
	{
		return buttonId;
	}

	public final void setLocation(Point location)
	{
		this.location = location;
	}

	private final void triggerOnButtonInteraction()
	{
		onButtonInteractionRefference.onButtonInteraction(buttonId);
	}

	private final boolean isButtonPressed()
	{
		return buttonPressed;
	}

	private final void setButtonPressed(boolean buttonPressed)
	{
		this.buttonPressed = buttonPressed;
	}
}
