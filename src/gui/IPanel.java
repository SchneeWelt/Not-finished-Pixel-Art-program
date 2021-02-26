package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import cons.Cons;

class IPanel extends JPanel
{
	private BufferedImage iImage = new BufferedImage(Cons.getScreenDimension().width, Cons.getScreenDimension().height,
			BufferedImage.TYPE_INT_RGB);

	public IPanel()
	{
		setSize(Cons.getScreenDimension());

		addMouseMotionListener(new MouseMotionListener()
		{

			@Override
			public void mouseMoved(MouseEvent e)
			{
				Cons.setMouseLocation(e.getPoint());
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				Cons.setMouseLocation(e.getPoint());
			}
		});

		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				Cons.setMousePressed(false);
				Cons.getMouseEventHandler().triggerOnMouseRelease(e);
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				Cons.setMousePressed(true);
				Cons.getMouseEventHandler().triggerOnMousePress(e);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
			}
		});
	}

	public final Graphics2D getIPanelGraphics()
	{
		return (Graphics2D) iImage.getGraphics();
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(iImage, 0, 0, null);
	}
}
