/**
 * 
 */
package balls.view.arrayofballs;

import javax.swing.ImageIcon;

/**
 * @author Przemysław Piórkowski
 *
 */
final class ImageLoader
{
	private final ImageIcon blue;
	private final ImageIcon green;
	private final ImageIcon violet;
	private final ImageIcon red;
	private final ImageIcon orange;
	private final ImageIcon empty;
	
	ImageLoader()
	{
		blue = new ImageIcon(getClass().getResource("/images/blue.gif"));
		green = new ImageIcon(getClass().getResource("/images/green.gif"));
		violet = new ImageIcon(getClass().getResource("/images/violet.gif"));
		red = new ImageIcon(getClass().getResource("/images/red.gif"));
		orange = new ImageIcon(getClass().getResource("/images/orange.gif"));
		empty = new ImageIcon(getClass().getResource("/images/empty.gif"));
	}

	/**
	 * @return the blue
	 */
	final ImageIcon getBlue()
	{
		return blue;
	}

	/**
	 * @return the green
	 */
	final ImageIcon getGreen()
	{
		return green;
	}

	/**
	 * @return the violet
	 */
	final ImageIcon getViolet()
	{
		return violet;
	}

	/**
	 * @return the red
	 */
	final ImageIcon getRed()
	{
		return red;
	}

	/**
	 * @return the orange
	 */
	final ImageIcon getOrange()
	{
		return orange;
	}

	/**
	 * @return the empty
	 */
	final ImageIcon getEmpty()
	{
		return empty;
	}
	
	
}
