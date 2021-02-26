package myObjects.hud.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import cons.Cons;
import handler.OnMousePress;
import handler.OnMouseRelease;
import myObjects.Draw;
import utility.GraphicsUtil;

public class Button implements Draw, OnMouseRelease, OnMousePress
{
	private boolean buttonPressed = false;

	private String buttonId = "null";
	private Point location = new Point();
	private Color defaultHitboxColor = Color.gray;
	private Dimension dimension = new Dimension();
	private ButtonConnector buttonConnector = null;
	private Color hitboxColor = defaultHitboxColor;
	private Color selectedHitboxColor = Color.white;

	public Button(Point location, Dimension dimension, String buttonId, ButtonConnector buttonConnector)
	{
		this.buttonId = buttonId;
		this.location = location;
		this.dimension = dimension;
		this.buttonConnector = buttonConnector;

		Cons.getMouseEventHandler().addOnMousePress(this);
		Cons.getMouseEventHandler().addOnMouseRelease(this);
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		displayHitbox(graphics);

		hitboxColor = defaultHitboxColor;

		if (isMouseAboveButton())
			onButtonSelect();
	}

	@Override
	public void onMousePress(MouseEvent e)
	{
		/* maus innerhalbt des Buttons */
		if (isMouseAboveButton())
		{
			onButtonPress();
			setButtonPressed(true);
		}
	}

	@Override
	public void onMouseRelease(MouseEvent e)
	{
		/* maus innerhalbt des Buttons */
		if (isMouseAboveButton() && isButtonPressed())
		{
			onButtonRelease();
			setButtonPressed(false);
		}
	}

	private final void onButtonPress()
	{
		/*
		 * wenn was reinkommt, sage dem buttonConnector bescheid, das dieser button
		 * gedrückt worden ist.
		 */

		buttonConnector.onButtonPress(buttonId);
	}

	private final void onButtonRelease()
	{
		buttonConnector.onButtonRelease(buttonId);
	}

	private final void onButtonSelect()
	{
		hitboxColor = selectedHitboxColor;
	}

	private final void displayHitbox(Graphics2D graphics)
	{
		graphics.setColor(hitboxColor);
		GraphicsUtil.drawRect(graphics, location, dimension);
	}
	
	public final String getButtonId()
	{
		return buttonId;
	}

	/**
	 * true, wenn die maus sich über diesem button befindet
	 * 
	 * @return
	 */

	private final boolean isMouseAboveButton()
	{
		return new Rectangle(location, dimension).contains(Cons.getMouseLocation());
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
