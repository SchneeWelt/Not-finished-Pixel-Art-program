package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader
{
	public static final BufferedImage loadImage(String filePath)
	{
		try
		{
			return ImageIO.read(new File(filePath));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

}
