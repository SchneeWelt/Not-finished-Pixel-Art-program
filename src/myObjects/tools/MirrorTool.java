package myObjects.tools;

import java.awt.Point;

import cons.Cons;
import myObjects.hud.screen.Screen;

class MirrorTool extends Tool
{
	/**
	 * Die mirrorAxis repräsentiet einen punkt auf dem Screen grid. Sagen wir die
	 * mirrorAxis liegt bei 2, 5. Das hieße dann, dass, wenn vertikal gespiegelt
	 * werden soll die beiden neuen pixel, wenn die maus auf einen pixel entfernt
	 * von 2 klick diese auf 1 und auf drei gemalt werden würden. Selbiges verhalten
	 * gill bei horizontaler spiegelung.
	 */
	private Point mirrorAxis = new Point();
	private MirrorMode mirrorMode = MirrorMode.vertical;

	public MirrorTool(Screen screen)
	{
		super(screen);
		centerMirror();
	}

	@Override
	protected void drawPixels()
	{
		/* startbedingungen prüfen */
		if (!Cons.isMousePressed())
			return;

		Point locationOnGrid = screen.getSelectedPixel();

		if (locationOnGrid == null)
			return;

		/* spiegel vorgang */
		if (mirrorMode.equals(MirrorMode.vertical))
			mirrorVertical(locationOnGrid);
		else if (mirrorMode.equals(MirrorMode.horizontal))
			mirrorHorizontal(locationOnGrid);
	}

	@Override
	protected void highlightPixels()
	{
		/* startbedingungen prüfen */
		if (Cons.isMousePressed())
			return;

		Point locationOnGrid = screen.getSelectedPixel();

		if (locationOnGrid == null)
			return;

		/* heighleight vorgang */
		if (mirrorMode.equals(MirrorMode.vertical))
			heighleightVertical(locationOnGrid);
		else if (mirrorMode.equals(MirrorMode.horizontal))
		{
			//heighleightHorizontal(locationOnGrid);
		}
	}

	@Override
	protected void defineToolId()
	{
		setToolId("mirrorTool");
	}

	/**
	 * Diese methode centriert die spiegel axe in der mitte.
	 * 
	 */

	private final void centerMirror()
	{
		mirrorAxis.x = screen.getGridDimension().width / 2; // eventuel hier noch - 1
		mirrorAxis.y = screen.getGridDimension().height / 2; // hier vieleicht auch -1
	}

	private final void heighleightVertical(Point pixelGridLocation)
	{
		/* makiere den ersten pixel */
		heighleightPixelAt(pixelGridLocation);

		if (isLeftLocated(pixelGridLocation))
		{
			/* ermittle abstand zu mirror axis */
			int dist = getVerticalDist(pixelGridLocation);

			/* markiere den zweiten pixel */
			Point pixelLocation = new Point(mirrorAxis.x + dist, pixelGridLocation.y);
			heighleightPixelAt(pixelLocation);
		} else if (isRightLocated(pixelGridLocation))
		{
			/* ermittle abstand zu mirror axis */
			int dist = getVerticalDist(pixelGridLocation) * -1;

			/* markiere den zweiten pixel */
			Point pixelLocation = new Point(mirrorAxis.x - dist, pixelGridLocation.y);

			heighleightPixelAt(pixelLocation);
		} else if (isEqualLocated(pixelGridLocation))
		{
			/* male den zweiten pixel. b.z.w lass ihn einfach weg... */
		}
	}

	private final void mirrorVertical(Point pixelGridLocation)
	{
		/* male den ersten pixel */
		drawPixelAt(pixelGridLocation);

		/* berechne den zweiten pixel */
		if (isLeftLocated(pixelGridLocation))
		{
			/* ermittle abstand zu mirror axis */
			int dist = getVerticalDist(pixelGridLocation);

			/* male den zweiten pixel */
			Point pixelLocation = new Point(mirrorAxis.x + dist, pixelGridLocation.y);

			drawPixelAt(pixelLocation);
		} else if (isRightLocated(pixelGridLocation))
		{
			/* ermittle abstand zu mirror axis */
			int dist = getVerticalDist(pixelGridLocation) * -1;

			/* male den zweiten pixel */
			Point pixelLocation = new Point(mirrorAxis.x - dist, pixelGridLocation.y);

			drawPixelAt(pixelLocation);
		} else if (isEqualLocated(pixelGridLocation))
		{
			/* male den zweiten pixel. b.z.w lass ihn einfach weg... */
		}
	}

	private final void mirrorHorizontal(Point pixelGridLocation)
	{
		System.err.println("mirror horizontal noch nicht fertig!!!");
	}

	/**
	 * gibt den abstand zur spiegel axe bei vertikaler spiegel position in pixeln
	 * zurück. Ist der Wert negatiev, so liegt der wert rechts von der spiegel axe,
	 * ist er hingegen positiv, so liegt er links von der spiegel axe.
	 * 
	 * @param locationOnGrid
	 * @return den abstand zur spiegel axe in pixeln
	 */

	private final int getVerticalDist(Point locationOnGrid)
	{
		return mirrorAxis.x - locationOnGrid.x;
	}

	/**
	 * ist wahr, wenn der punkt locationOnGrid sich links von der vertikalen spiegel
	 * axe befindet.
	 * 
	 * @param
	 * @return
	 */

	private final boolean isLeftLocated(Point locationOnGrid)
	{
		return locationOnGrid.x < mirrorAxis.x;
	}

	/**
	 * ist wahr, wenn der punkt locationOnGrid sich rechts von der vertikalen
	 * spiegel axe befindet.
	 * 
	 * @param
	 * @return
	 */

	private final boolean isRightLocated(Point locationOnGrid)
	{
		return locationOnGrid.x > mirrorAxis.x;
	}

	/**
	 * ist war, wenn locationOnGrid vertikal gepspiegelt genau auf der spiegel axe
	 * liegt.
	 * 
	 * @param locationOnGrid
	 * @return
	 */

	private final boolean isEqualLocated(Point locationOnGrid)
	{
		return locationOnGrid.x == mirrorAxis.x;
	}

	/**
	 * Vertikal heißt, dass der Spiegel vertikal im raum liegt. Horizontal heißt,
	 * dass der Spiegel horizontal im Raum liegt.
	 * 
	 * @author jaapi
	 *
	 */
	private enum MirrorMode
	{
		vertical, horizontal;
	}

	/**
	 * Die mirrorAxis ist der Punkt auf dem Grid des Screen Objectes, auf dem
	 * gespiegelt wird.
	 * 
	 * @param mirrorAxis
	 */

	public final void setMirrorAxis(Point mirrorAxis)
	{
		this.mirrorAxis = mirrorAxis;
	}
}
