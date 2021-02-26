package myObjects.tools;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import cons.Cons;
import handler.OnKeyPress;
import handler.OnKeyRelease;
import handler.OnMousePress;
import handler.OnMouseRelease;
import myObjects.Draw;
import myObjects.hud.screen.Screen;

/**
 * This sorgt dafür, dass nur ein Tool zur zeit existiert. This enthällt während
 * der Laufzeit alle Tools, die dem Programm zur verfügung stehen. Von hier aus
 * werden die Tools erstellt und sind von hier aus abrufbar.
 * 
 * This besitz eine Verbindung zu dem KeyEvent und zum MouseEvent stream. Mit
 * anderen Worten, diese Klasse kann onKeyPress(), onKeyRelease(),
 * onMouseRelease() u.s.w verarbeiten.
 * 
 * @author jaapi
 *
 */

public class ToolSwitch implements Draw, OnKeyPress, OnKeyRelease, OnMouseRelease, OnMousePress
{
	private BurnTool burnTool = null;
	private PencilTool pencilTool = null;
	private EraserTool eraserTool = null;
	private MirrorTool mirrorTool = null;
	private ColorPickerTool colorPickerTool = null;
	private FullColorBucketTool fullColorBucketTool = null;
	private ExternColorPickerTool externColorPickerTool = null;
	private SwitchState switchState = SwitchState.pencilTool;

	public ToolSwitch(Screen screen)
	{
		/* init tools */
		burnTool = new BurnTool(screen);
		pencilTool = new PencilTool(screen);
		eraserTool = new EraserTool(screen);
		mirrorTool = new MirrorTool(screen);
		colorPickerTool = new ColorPickerTool(screen);
		fullColorBucketTool = new FullColorBucketTool(screen);
		externColorPickerTool = new ExternColorPickerTool(screen);

		/* connect event streams */
		Cons.getKeyEventHandler().addOnKeyPress(this);
		Cons.getKeyEventHandler().addOnKeyRelease(this);
		Cons.getMouseEventHandler().addOnMousePress(this);
		Cons.getMouseEventHandler().addOnMouseRelease(this);

		setSwitchState(SwitchState.eraserTool);
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		switch (switchState)
		{
			case pencilTool:
				pencilTool.draw(graphics);
				break;

			case eraserTool:
				eraserTool.draw(graphics);
				break;

			case mirrorTool:
				mirrorTool.draw(graphics);
				break;

			case burnTool:
				burnTool.draw(graphics);
				break;

			case fullColorBucketTool:
				fullColorBucketTool.draw(graphics);
				break;

			case colorPickerTool:
				colorPickerTool.draw(graphics);
				break;

			case externColorPickerTool:
				externColorPickerTool.draw(graphics);
				break;

			case pixelShiftTool:
				break;

			default:
				break;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e)
	{
		switch (switchState)
		{
			case externColorPickerTool:
				externColorPickerTool.onKeyRelease(e);
				break;

			case pixelShiftTool:
				break;

			default:
				break;
		}
	}

	@Override
	public void onKeyPress(KeyEvent e)
	{
		switch (switchState)
		{
			default:
				break;
		}
	}

	@Override
	public void onMousePress(MouseEvent e)
	{

	}

	@Override
	public void onMouseRelease(MouseEvent e)
	{
		switch (switchState)
		{
			case burnTool:
				burnTool.onMouseRelease(e);
				break;

			case fullColorBucketTool:
				fullColorBucketTool.onMouseRelease(e);
				break;

			default:
				break;
		}
	}

	public final void setSwitchState(SwitchState switchState)
	{
		this.switchState = switchState;
	}

	public final MirrorTool getMirrorTool()
	{
		return mirrorTool;
	}

	public final PencilTool getPencilTool()
	{
		return pencilTool;
	}

	public final EraserTool getEraserTool()
	{
		return eraserTool;
	}

	public final ColorPickerTool getColorPickerTool()
	{
		return colorPickerTool;
	}

	public final ExternColorPickerTool getExternColorPickerTool()
	{
		return externColorPickerTool;
	}

	public final SwitchState getSwitchState()
	{
		return switchState;
	}

	public enum SwitchState
	{
		pencilTool, fullColorBucketTool, eraserTool, burnTool, mirrorTool, pixelShiftTool, colorPickerTool,
		externColorPickerTool;
	}
}
