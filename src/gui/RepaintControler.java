package gui;

import java.awt.Dimension;
import java.awt.Point;

public class RepaintControler
{
	private IFrame iFrame = null;
	private static RepaintControler repaintControler = null;

	public RepaintControler(IFrame iFrame)
	{
		this.iFrame = iFrame;
	}
	
	public final void repaintIPanel(Point startLocation, Dimension repaintDimension)
	{
		iFrame.repaintIPanel(startLocation, repaintDimension);
	}

	public static final RepaintControler getRepaintControler()
	{
		return repaintControler;
	}

	public static final void initReapaintControler(IFrame iFrame)
	{
		repaintControler = (repaintControler == null) ? repaintControler = new RepaintControler(iFrame) : repaintControler;
	}
}
