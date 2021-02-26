package myObjects.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import cons.Cons;
import myObjects.Draw;
import myObjects.hud.screen.Pixel;
import myObjects.hud.screen.Screen;

public abstract class Tool implements Draw
{
	protected Screen screen = null;
	protected String toolId = "tool";
	protected static Color toolColor = Color.pink;
	protected static ToolWidth toolWidth = ToolWidth.small;

	/**
	 * 
	 * 
	 * @param screen Das screen object auf dem gemalt werden soll
	 */
	public Tool(Screen screen)
	{
		defineToolId();
		this.screen = screen;
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		drawPixels();
		highlightPixels();
	}

	/**
	 * Ist die maus nicht gedrückt und existiert der pixel auf dem screenobject
	 * grid, dann malt diese Methode einen Pixel auf die position, wo sich die Maus
	 * gerade befindet. Wie groß dieser Pixel ist, ist abhängig von der toolWidth. 
	 */

	protected void drawPixels()
	{
		if (!Cons.isMousePressed())
			return;

		Point pixelGridLocation = screen.getSelectedPixel();

		if (!isValidGridLocation(pixelGridLocation))
			return;

		drawPixelAt(pixelGridLocation);
	}

	/**
	 * Wenn sich das Tool über einem Pixel befindet und die maus nicht gedrückt
	 * wird, gibt es dem Pixel und anliegenden, dies ist abhängig von der toolWidth
	 * und anderen paramtern, wie z.B. ob pixel gespiegelt werden sollen den befehlt sich
	 * zu heighlighten.
	 * 
	 */

	protected void highlightPixels()
	{
		if (Cons.isMousePressed())
			return;

		Point pixelGridLocation = screen.getSelectedPixel();

		if (!isValidGridLocation(pixelGridLocation))
			return;

		heighleightPixelAt(pixelGridLocation);
	}

	/**
	 * Das ist, wie pixels auf dem Screen object gemalt werden sollten. Diese
	 * Methode malt einen Pixel auf das screen object. Die verwendete farbe für
	 * diesen neuen pixel ist hierbei die toolColor. Die methode bezieht außerdem
	 * noch die ToolWidth mit ein. Ist diese z.B auf large gestellt, so wird ein
	 * großer pixel gemalt.
	 * 
	 * 
	 * @param locationOnGrid die position des pixels, wo dieser auf dem grid gemalt
	 *                       werden soll.
	 */

	protected final void drawPixelAt(Point locationOnGrid)
	{
		if (toolWidth.equals(ToolWidth.small))
			drawSmallPixel(locationOnGrid);
		else if (toolWidth.equals(ToolWidth.medium))
			drawMediumPixel(locationOnGrid);
		else if (toolWidth.equals(ToolWidth.large))
			drawLargePixel(locationOnGrid);
	}

	/**
	 * Das ist, wie wie pixel geheileighted werde sollten. Die methode heighleighted
	 * den pixel auf pixelLocationOnGrid. Es wird außerdem noch die ToolWidth
	 * miteinbezogen. Ist dise z.B auf large, so wird ein großer pixel
	 * geheighleitet.
	 * 
	 * 
	 * @param pixelLocationOnGrid
	 */

	protected void heighleightPixelAt(Point pixelLocationOnGrid)
	{
		if (toolWidth.equals(ToolWidth.small))
			heighlightSmallPixel(pixelLocationOnGrid);
		else if (toolWidth.equals(ToolWidth.medium))
			heighlightMediumPixel(pixelLocationOnGrid);
		else if (toolWidth.equals(ToolWidth.large))
			heightleightLargePixel(pixelLocationOnGrid);
	}

	/**
	 * Hier sollte festgelegt werden, welche id das zugrunde liegende tool object
	 * besitz. Diese Methode wird im Konstruktor der Tool class aufgerufen. Die
	 * default tool id ist: "tool". Meine convention ist die tools ids klein zu
	 * schreiben.
	 */

	protected abstract void defineToolId();

	protected void drawSmallPixel(Point gridLocation)
	{
		if (!isValidGridLocation(gridLocation))
			return;

		Pixel pixel = screen.getPixelAt(gridLocation);
		pixel.setColor(toolColor);
	}

	protected void heighlightSmallPixel(Point gridLocation)
	{
		if (!isValidGridLocation(gridLocation))
			return;

		Pixel pixel = screen.getPixelAt(gridLocation);
		pixel.setHightlightPixel(true);
	}

	private void drawMediumPixel(Point gridLocation)
	{
		Point leftUp = new Point(gridLocation.x - 1, gridLocation.y - 1);
		Point leftDown = new Point(gridLocation.x - 1, gridLocation.y);
		Point rightUp = new Point(gridLocation.x, gridLocation.y - 1);
		Point rightDown = new Point(gridLocation.x, gridLocation.y);

		drawSmallPixel(leftUp);
		drawSmallPixel(rightUp);
		drawSmallPixel(leftDown);
		drawSmallPixel(rightDown);
	}

	private void heighlightMediumPixel(Point gridLocation)
	{
		Point leftUp = new Point(gridLocation.x - 1, gridLocation.y - 1);
		Point leftDown = new Point(gridLocation.x - 1, gridLocation.y);
		Point rightUp = new Point(gridLocation.x, gridLocation.y - 1);
		Point rightDown = new Point(gridLocation.x, gridLocation.y);

		heighlightSmallPixel(leftUp);
		heighlightSmallPixel(rightUp);
		heighlightSmallPixel(leftDown);
		heighlightSmallPixel(rightDown);
	}

	private void drawLargePixel(Point gridLocation)
	{
		Point center = new Point(gridLocation);
		Point leftCenter = new Point(gridLocation.x - 1, gridLocation.y);
		Point rightCenter = new Point(gridLocation.x + 1, gridLocation.y);

		Point centerUp = new Point(gridLocation.x, gridLocation.y - 1);
		Point leftUp = new Point(gridLocation.x - 1, gridLocation.y - 1);
		Point rightUp = new Point(gridLocation.x + 1, gridLocation.y - 1);

		Point centerDown = new Point(gridLocation.x, gridLocation.y + 1);
		Point leftDown = new Point(gridLocation.x - 1, gridLocation.y + 1);
		Point upDown = new Point(gridLocation.x + 1, gridLocation.y + 1);

		drawSmallPixel(center);
		drawSmallPixel(leftCenter);
		drawSmallPixel(rightCenter);

		drawSmallPixel(centerUp);
		drawSmallPixel(leftUp);
		drawSmallPixel(rightUp);

		drawSmallPixel(centerDown);
		drawSmallPixel(leftDown);
		drawSmallPixel(upDown);
	}

	private void heightleightLargePixel(Point gridLocation)
	{
		Point center = new Point(gridLocation);
		Point leftCenter = new Point(gridLocation.x - 1, gridLocation.y);
		Point rightCenter = new Point(gridLocation.x + 1, gridLocation.y);

		Point centerUp = new Point(gridLocation.x, gridLocation.y - 1);
		Point leftUp = new Point(gridLocation.x - 1, gridLocation.y - 1);
		Point rightUp = new Point(gridLocation.x + 1, gridLocation.y - 1);

		Point centerDown = new Point(gridLocation.x, gridLocation.y + 1);
		Point leftDown = new Point(gridLocation.x - 1, gridLocation.y + 1);
		Point upDown = new Point(gridLocation.x + 1, gridLocation.y + 1);

		heighlightSmallPixel(center);
		heighlightSmallPixel(leftCenter);
		heighlightSmallPixel(rightCenter);

		heighlightSmallPixel(centerUp);
		heighlightSmallPixel(leftUp);
		heighlightSmallPixel(rightUp);

		heighlightSmallPixel(centerDown);
		heighlightSmallPixel(leftDown);
		heighlightSmallPixel(upDown);
	}

	/**
	 * Diese Datenbank gibt vor, wie dick der stift mal. small malt einen Pixel.
	 * medium malt 4 Pixel, in rechteckiger form. large malt 9 Pixel in rechteckiger
	 * form.
	 * 
	 * @author jaapi
	 *
	 */

	public enum ToolWidth
	{
		small, medium, large;
	}

	public static final ToolWidth getToolWidth()
	{
		return toolWidth;
	}

	public static final void setToolWidth(ToolWidth toolWidth)
	{
		Tool.toolWidth = toolWidth;
	}

	public static final Color getToolColor()
	{
		return toolColor;
	}

	public final String getToolId()
	{
		return toolId;
	}

	public static final void setToolColor(Color toolColor)
	{
		Tool.toolColor = toolColor;
	}

	/**
	 * überprüft, ob die input gridLocation auf dem screen existiert. Dabei wird
	 * zunächt getestet, ob der punkt nicht null ist anschließend wird dann
	 * überprüft, ob sich der punkt außerhalb oder innerhalb des grids befindet.
	 */

	protected final boolean isValidGridLocation(Point gridLocation)
	{
		if (gridLocation == null)
			return false;

		screen.getGridDimension();

		boolean first = gridLocation.x < screen.getGridDimension().width;
		boolean second = gridLocation.y < screen.getGridDimension().height;
		boolean third = gridLocation.x > -1;
		boolean fourth = gridLocation.y > -1;

		return (first && second && third && fourth);
	}

	protected final void setToolId(String toolId)
	{
		this.toolId = toolId;
	}
}
