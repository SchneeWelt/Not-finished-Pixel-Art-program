package myObjects.tools;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import handler.OnKeyRelease;
import myObjects.hud.screen.Screen;

/**
 * Das ColorPicker tool kann farbe von pixeln auf dem screen object auslesen.
 * Das ExtenrColorPickerTool hingegen kann farbe auf dem Bildschirm des Computer
 * auslesen. Dafür muss die Maus über den gewünschten Farbwert gehalten werden
 * und dann die Taste enter gedrückt werden.
 * 
 * @author jaapi
 *
 */

class ExternColorPickerTool extends ColorPickerTool implements OnKeyRelease
{
	private final int COLOR_PICK_KEY = KeyEvent.VK_ENTER;

	public ExternColorPickerTool(Screen screen)
	{
		super(screen);
	}

	@Override
	public void onKeyRelease(KeyEvent e)
	{
		var key = e.getKeyCode();

		if (key == COLOR_PICK_KEY)
			pickColor(e);
	}

	@Override
	protected void defineToolId()
	{
		setToolId("externColorPickerTool");
	}

	/**
	 * Jetzt heighleightet diese methode gar keine pixel mehr auf dem screen object.
	 * 
	 */

	@Override
	protected void heighleightPixelAt(Point pixelLocationOnGrid)
	{
		/* do nothing */
	}

	@Override
	protected void pickColor()
	{
		/* do nothing */
	}

	/**
	 * Diese methode setzt die toolColor auf die farbe, auf die die Maus im moment
	 * des aufrufes zeigt. Hierbei ist der ganze screen ein farb auswahl
	 * möglichkeit.
	 * 
	 * @param e
	 */

	protected final void pickColor(KeyEvent e)
	{
		Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
		Color newToolColor = getColorAt(mouseLocation);

		Tool.setToolColor(newToolColor);
	}

	private final Color getColorAt(Point locationOnScreen)
	{
		return getRobot().getPixelColor(locationOnScreen.x, locationOnScreen.y);
	}

	private final Robot getRobot()
	{
		try
		{
			return new Robot();
		} catch (AWTException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
